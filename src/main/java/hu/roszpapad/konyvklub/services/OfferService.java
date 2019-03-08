package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;

public interface OfferService {

    Offer findById(Long id);
    Offer createOffer(OfferToBeSavedDTO offerDTO);
    Offer updateOffer(OfferToBeUpdatedDTO offer);
    void deleteOffer(Long offerId);
    void acceptOffer(Ticket ticket, Offer offer);
    Offer rejectOffer(Offer offer);
}
