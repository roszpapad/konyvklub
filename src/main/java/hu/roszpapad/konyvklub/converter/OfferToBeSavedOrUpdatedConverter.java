package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdated;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeSavedOrUpdatedConverter implements Converter<Offer, OfferToBeSavedOrUpdated> {

    private final TicketRepository ticketRepository;

    @Override
    public OfferToBeSavedOrUpdated toDTO(Offer entity) {
        OfferToBeSavedOrUpdated offerDTO = new OfferToBeSavedOrUpdated();
        offerDTO.setBookToPay(entity.getBookToPay());
        offerDTO.setId(entity.getId());
        offerDTO.setCustomer(entity.getCustomer());
        offerDTO.setStatus(entity.getStatus());
        offerDTO.setTicketId(entity.getTicket().getId());

        return offerDTO;
    }

    @Override
    public Offer toEntity(OfferToBeSavedOrUpdated dto) {
        Offer offer = new Offer();
        offer.setId(dto.getId());
        offer.setCustomer(dto.getCustomer());
        offer.setStatus(dto.getStatus());
        offer.setBookToPay(dto.getBookToPay());

        return offer;
    }
}
