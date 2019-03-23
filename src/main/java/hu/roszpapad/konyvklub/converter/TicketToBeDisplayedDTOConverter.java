package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketToBeDisplayedDTOConverter {

    private final UserToBeDisplayedDTOConverter userToBeDisplayedDTOConverter;

    private final BookToBeDisplayedDTOConverter bookToBeDisplayedDTOConverter;

    private final OfferToBeDisplayedDTOConverter offerToBeDisplayedDTOConverter;

    public TicketToBeDisplayedDTO toDTO(Ticket entity){

        TicketToBeDisplayedDTO ticketDTO = new TicketToBeDisplayedDTO();
        ticketDTO.setBookToSell(bookToBeDisplayedDTOConverter.toDTO(entity.getBookToSell()));
        ticketDTO.setDescription(entity.getDescription());
        ticketDTO.setEndDate(entity.getEndDate());
        ticketDTO.setId(entity.getId());

        List<OfferToBeDisplayedDTO> offerDTOs = new ArrayList<>();
        entity.getOffers().forEach(offer -> offerDTOs.add(offerToBeDisplayedDTOConverter.toDTO(offer)));
        ticketDTO.setOffers(offerDTOs);

        ticketDTO.setSeller(userToBeDisplayedDTOConverter.toDTO(entity.getSeller()));
        return ticketDTO;
    }
}
