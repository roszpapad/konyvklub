package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdated;
import hu.roszpapad.konyvklub.model.Offer;

public interface OfferService {

    Offer saveOfferDTO(OfferToBeSavedOrUpdated offerDTO);
}
