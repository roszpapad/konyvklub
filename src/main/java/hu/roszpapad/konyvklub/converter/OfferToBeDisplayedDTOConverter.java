package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeDisplayedDTOConverter {

    private final BookToBeDisplayedDTOConverter bookToBeDisplayedDTOConverter;
    private final UserToBeDisplayedDTOConverter userToBeDisplayedDTOConverter;

    public OfferToBeDisplayedDTO toDTO(Offer entity) {
        OfferToBeDisplayedDTO offerDTO = new OfferToBeDisplayedDTO();
        offerDTO.setBookToPay(bookToBeDisplayedDTOConverter.toDTO(entity.getBookToPay()));
        offerDTO.setCustomer(userToBeDisplayedDTOConverter.toDTO(entity.getCustomer()));
        offerDTO.setId(entity.getId());
        offerDTO.setStatus(entity.getStatus());
        offerDTO.setTicketId(entity.getTicket().getId());
        offerDTO.setDescription(entity.getDescription());
        return offerDTO;
    }
}
