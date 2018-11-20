package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeUpdatedDTOConverter implements Converter<Offer, OfferToBeUpdatedDTO> {

    private final TicketRepository ticketRepository;

    @Override
    public OfferToBeUpdatedDTO toDTO(Offer entity) {
        OfferToBeUpdatedDTO offerDTO = new OfferToBeUpdatedDTO();
        offerDTO.setBookToPay(entity.getBookToPay());
        offerDTO.setId(entity.getId());
        offerDTO.setCustomer(entity.getCustomer());
        offerDTO.setStatus(entity.getStatus());
        offerDTO.setTicketId(entity.getTicket().getId());

        return offerDTO;
    }

    @Override
    public Offer toEntity(OfferToBeUpdatedDTO dto) {
        Offer offer = new Offer();
        offer.setId(dto.getId());
        offer.setCustomer(dto.getCustomer());
        offer.setStatus(dto.getStatus());
        offer.setBookToPay(dto.getBookToPay());

        return offer;
    }
}
