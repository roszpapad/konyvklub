package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Status;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.Collection;

public interface OfferService {

    Offer findById(Long id);
    Offer createOffer(Offer offer);
    Offer updateOffer(Offer offer);
    Collection<Offer> getOffersByStatusAndTicket(Status status, Ticket ticket);
    void deleteOffer(Long offerId);
    Ticket acceptOffer(Ticket ticket, Offer offer);
    Offer rejectOffer(Offer offer);
}
