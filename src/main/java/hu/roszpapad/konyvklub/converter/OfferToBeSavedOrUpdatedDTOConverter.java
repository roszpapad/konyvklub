package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdatedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeSavedOrUpdatedDTOConverter implements Converter<Offer, OfferToBeSavedOrUpdatedDTO> {

    private final TicketRepository ticketRepository;

    @Override
    public OfferToBeSavedOrUpdatedDTO toDTO(Offer entity) {
        OfferToBeSavedOrUpdatedDTO offerDTO = new OfferToBeSavedOrUpdatedDTO();
        offerDTO.setBookToPay(entity.getBookToPay());
        offerDTO.setId(entity.getId());
        offerDTO.setCustomer(entity.getCustomer());
        offerDTO.setStatus(entity.getStatus());
        offerDTO.setTicketId(entity.getTicket().getId());

        return offerDTO;
    }

    @Override
    public Offer toEntity(OfferToBeSavedOrUpdatedDTO dto) {
        Offer offer = new Offer();
        offer.setId(dto.getId());
        offer.setCustomer(dto.getCustomer());
        offer.setStatus(dto.getStatus());
        offer.setBookToPay(dto.getBookToPay());

        return offer;
    }
}
