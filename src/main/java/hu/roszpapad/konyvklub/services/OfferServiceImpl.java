package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeUpdatedDTO;
import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.exceptions.OfferCantBeUpdatedException;
import hu.roszpapad.konyvklub.exceptions.TicketClosedException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final UserService userService;

    private final OfferRepository offerRepository;

    private final BookRepository bookRepository;

    private final TicketRepository ticketRepository;

    private final NotificationService notificationService;

    private final ChatChannelService chatChannelService;

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new NotFoundException(Offer.class));
    }

    @Override
    public Offer createOffer(OfferToBeSavedDTO offerDTO) {

        Offer offerToBeSaved = new Offer();
        Book book = bookRepository.findById(offerDTO.getBookId()).orElseThrow(() -> new NotFoundException(Book.class));
        Ticket ticket = ticketRepository.findById(offerDTO.getTicketId()).orElseThrow(() -> new NotFoundException(Ticket.class));
        if (ticket.getEndDate().isBefore(LocalDateTime.now())){
            throw new TicketClosedException("A ticketre nem lehet már ajánlani.");
        }
        offerToBeSaved.setTicket(ticket);
        offerToBeSaved.setBookToPay(book);
        offerToBeSaved.setDescription(offerDTO.getDescription());

        User customer = book.getOwner();
        offerToBeSaved.setCustomer(customer);

        book.setOfferable(false);
        offerToBeSaved.setStatus(Status.PENDING);
        Offer savedOffer = offerRepository.save(offerToBeSaved);
        customer.getOffersInInterest().add(savedOffer);
        userService.saveUser(customer);
        return savedOffer;
    }

    @Override
    public Offer updateOffer(OfferToBeUpdatedDTO offer) {

        Offer current = findById(offer.getId());
        if (!current.getStatus().equals(Status.PENDING)){
            throw new OfferCantBeUpdatedException("Az offer nem updatelhető, mert már elutasították.");
        }

        current.setDescription(offer.getDescription());
        Book currentBook = current.getBookToPay();
        Book book = bookRepository.findById(offer.getBookId()).orElseThrow(()-> new NotFoundException(Book.class));
        if (!book.getId().equals(currentBook.getId())){
            currentBook.setOfferable(true);
            bookRepository.save(currentBook);
            book.setOfferable(false);
            bookRepository.save(book);
            current.setBookToPay(book);
        }

        return offerRepository.save(current);
    }

    @Override
    public void deleteOffer(Long offerId) {
        Offer current = findById(offerId);
        if (current.getStatus().equals(Status.PENDING)) {
            current.getBookToPay().setOfferable(true);
            bookRepository.save(current.getBookToPay());
        }
        Ticket ticket = current.getTicket();
        ticket.getOffers().remove(current);
        ticketRepository.save(ticket);
        userService.removeOfferFromUser(current.getCustomer(), current);
        offerRepository.delete(current);
    }

    @Override
    public void acceptOffer(Ticket ticket, Offer offer) {

        List<Offer> notAcceptedOffers = ticket.getOffers();
        notAcceptedOffers.remove(offer);
        notAcceptedOffers.forEach(offer1 -> {
            userService.removeOfferFromUser(offer1.getCustomer(), offer1);
            if (offer1.getStatus().equals(Status.PENDING))
                rejectOffer(offer1);

        });

        Book soldBook = ticket.getBookToSell();
        Book paidBook = offer.getBookToPay();

        User seller = ticket.getSeller();
        User customer = offer.getCustomer();

        seller.getTicketsCreated().remove(ticket);
        customer.getOffersInInterest().remove(offer);
        soldBook.setOfferable(true);
        paidBook.setOfferable(true);
        

        userService.changeBookBetweenUsers(seller,soldBook,customer,paidBook);
        ChatChannel chatChannel = chatChannelService
                .createBusinessChatChannel(seller.getUsername(),customer.getUsername(),soldBook.getTitle(),paidBook.getTitle());
        notificationService.createAcceptedOfferNotification(offer,chatChannel.getId());
        notificationService.createAcceptedTicketNotification(offer,chatChannel.getId());
        offerRepository.delete(offer);
        offerRepository.deleteAll(ticket.getOffers());
        ticketRepository.delete(ticket);
    }

    public Offer rejectOffer(Offer offer){
        offer.setStatus(Status.REJECTED);
        offer.getBookToPay().setOfferable(true);
        notificationService.createRejectedOfferNotification(offer);
        return offerRepository.save(offer);
    }


}
