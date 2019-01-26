package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketToBeDisplayedDTOConverter implements Converter<Ticket,TicketToBeDisplayedDTO>{

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedDTOConverter;

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;

    private final Converter<Offer, OfferToBeDisplayedDTO> offerToBeDisplayedDTOConverter;

    @Override
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

    @Override
    public Ticket toEntity(TicketToBeDisplayedDTO dto) {

        Ticket ticket = new Ticket();
        ticket.setDescription(dto.getDescription());
        ticket.setId(dto.getId());
        ticket.setBookToSell(bookToBeDisplayedDTOConverter.toEntity(dto.getBookToSell()));
        ticket.setSeller(userToBeDisplayedDTOConverter.toEntity(dto.getSeller()));
        ticket.setEndDate(dto.getEndDate());
        //TODO : ticket.setOffers();
        return ticket;
    }
}
