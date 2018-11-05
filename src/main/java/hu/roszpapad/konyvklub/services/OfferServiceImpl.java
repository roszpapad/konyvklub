package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.OfferNotFoundException;
import hu.roszpapad.konyvklub.exceptions.TicketExpiredOrNotOpenException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final UserService userService;

    private final OfferRepository offerRepository;

    private final BookService bookService;

    private final TicketRepository ticketRepository;

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException());
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Collection<Offer> getOffersByStatusAndTicket(Status status, Ticket ticket) {
        return offerRepository.findAllByStatusAndTicket(status,ticket);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        //TODO: if (!offer.getStatus().equals(Status.PENDING)) throw vmi exception;
        Offer current = offerRepository.findById(offer.getId()).orElseThrow(() -> new OfferNotFoundException());

        current.setBookToPay(offer.getBookToPay());
        return current;
    }

    @Override
    public void deleteOffer(Long offerId) {
        Offer current = offerRepository.findById(offerId).
                orElseThrow(() -> new OfferNotFoundException());
        bookService.freeBook(current.getBookToPay());
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
        rejectPendingOffers(notAcceptedOffers);

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

    public void rejectPendingOffers(List<Offer> offers) {
        offers.stream().filter(offer -> offer.getStatus().equals(Status.PENDING))
                .forEach(offer -> rejectOffer(offer));
    }

    public void rejectOffer(Offer offer){
        offer.setStatus(Status.REJECTED);
        bookService.freeBook(offer.getBookToPay());
    }
}
