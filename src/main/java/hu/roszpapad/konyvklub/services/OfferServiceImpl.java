package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.OfferNotFoundException;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Status;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final UserService userService;

    private final OfferRepository offerRepository;

    private final BookService bookService;

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
}
