package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.OfferCantBeUpdatedException;
import hu.roszpapad.konyvklub.exceptions.OfferNotFoundException;
import hu.roszpapad.konyvklub.exceptions.TicketExpiredOrNotOpenException;
import hu.roszpapad.konyvklub.model.*;
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

    private final BookService bookService;

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException());
    }

    @Override
    public Offer createOffer(Offer offer) {
        User customer = offer.getBookToPay().getOwner();
        offer.setCustomer(customer);
        customer.getOffersInInterest().add(offer);
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
        return current;
    }

    @Override
    public void deleteOffer(Long offerId) {
        Offer current = findById(offerId);
        if (current.getStatus().equals(Status.PENDING))
            bookService.freeBook(current.getBookToPay());

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
        notAcceptedOffers.forEach(offer1 -> rejectOffer(offer1));

        Book soldBook = ticket.getBookToSell();
        Book paidBook = offer.getBookToPay();

        User seller = ticket.getSeller();
        User customer = offer.getCustomer();

        bookService.freeBook(soldBook);
        bookService.freeBook(paidBook);

        userService.changeBookBetweenUsers(seller,soldBook,customer,paidBook);

        ticketRepository.save(ticket);
        return ticket;
    }

    public void rejectOffer(Offer offer){
        if (offer.getStatus().equals(Status.PENDING)) {
            offer.setStatus(Status.REJECTED);
            bookService.freeBook(offer.getBookToPay());
            offerRepository.save(offer);
        }
    }
}
