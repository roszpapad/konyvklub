package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.OfferCantBeUpdatedException;
import hu.roszpapad.konyvklub.exceptions.OfferNotFoundException;
import hu.roszpapad.konyvklub.exceptions.TicketExpiredOrNotOpenException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final UserService userService;

    private final UserRepository userRepository;

    private final OfferRepository offerRepository;

    private final BookRepository bookRepository;

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    private final NotificationService notificationService;

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException());
    }

    @Override
    public Offer createOffer(Offer offer) {
        User customer = offer.getBookToPay().getOwner();
        offer.setCustomer(customer);
        customer.getOffersInInterest().add(offer);
        offer.getBookToPay().setOfferable(false);
        userRepository.save(customer);
        return offerRepository.save(offer);
    }

    @Override
    public Collection<Offer> getOffersByStatusAndTicket(Status status, Ticket ticket) {
        return offerRepository.findAllByStatusAndTicket(status,ticket);
    }

    @Override
    public Offer updateOffer(Offer offer) {

        Offer current = findById(offer.getId());
        if (!current.getStatus().equals(Status.PENDING)){
            throw new OfferCantBeUpdatedException();
        }

        current.setDescription(offer.getDescription());
        current.setBookToPay(offer.getBookToPay());
        return offerRepository.save(current);
    }

    @Override
    public void deleteOffer(Long offerId) {
        Offer current = findById(offerId);
        if (current.getStatus().equals(Status.PENDING)) {
            current.getBookToPay().setOfferable(true);
            bookRepository.save(current.getBookToPay());
        }
        ticketService.removeOfferFromTicket(current.getTicket(), current);
        userService.removeOfferFromUser(current.getCustomer(), current);
        offerRepository.delete(current);
    }

    @Override
    public Ticket acceptOffer(Ticket ticket, Offer offer) {
        if (!ticket.isOpen())
            throw new TicketExpiredOrNotOpenException();

        offer.setStatus(Status.ACCEPTED);
        ticket.setOpen(false);

        List<Offer> notAcceptedOffers = ticket.getOffers();
        notAcceptedOffers.remove(offer);
        notAcceptedOffers.forEach(offer1 -> {
            if (offer1.getStatus().equals(Status.PENDING))
                rejectOffer(offer1);
        });

        Book soldBook = ticket.getBookToSell();
        Book paidBook = offer.getBookToPay();

        User seller = ticket.getSeller();
        User customer = offer.getCustomer();

        soldBook.setOfferable(true);
        paidBook.setOfferable(true);
        /*bookRepository.save(soldBook);
        bookRepository.save(paidBook);*/

        userService.changeBookBetweenUsers(seller,soldBook,customer,paidBook);
        notificationService.createAcceptedOfferNotification(offer);
        notificationService.createAcceptedTicketNotification(offer);

        return ticketRepository.save(ticket);
    }

    public Offer rejectOffer(Offer offer){
        offer.setStatus(Status.REJECTED);
        offer.getBookToPay().setOfferable(true);
        notificationService.createRejectedOfferNotification(offer);
        return offerRepository.save(offer);
    }
}
