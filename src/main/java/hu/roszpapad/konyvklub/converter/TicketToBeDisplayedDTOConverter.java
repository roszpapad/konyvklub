package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketToBeDisplayedDTOConverter implements Converter<Ticket,TicketToBeDisplayedDTO>{

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedDTOConverter;

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;

    @Override
    public TicketToBeDisplayedDTO toDTO(Ticket entity){

        TicketToBeDisplayedDTO ticketDTO = new TicketToBeDisplayedDTO();
        ticketDTO.setBookToSell(bookToBeDisplayedDTOConverter.toDTO(entity.getBookToSell()));
        ticketDTO.setDescription(entity.getDescription());
        ticketDTO.setEndDate(entity.getEndDate());
        ticketDTO.setId(entity.getId());
        //TODO : offerDTOt csinalni ticketDTO.setOffers();
        ticketDTO.setOffers(entity.getOffers());
        ticketDTO.setOpen(entity.isOpen());
        ticketDTO.setSeller(userToBeDisplayedDTOConverter.toDTO(entity.getSeller()));
        return ticketDTO;
    }

    @Override
    public Ticket toEntity(TicketToBeDisplayedDTO dto) {

        Ticket ticket = new Ticket();
        ticket.setDescription(dto.getDescription());
        ticket.setId(dto.getId());
        ticket.setBookToSell(bookToBeDisplayedDTOConverter.toEntity(dto.getBookToSell()));
        ticket.setOpen(dto.getOpen());
        ticket.setSeller(userToBeDisplayedDTOConverter.toEntity(dto.getSeller()));
        ticket.setEndDate(dto.getEndDate());
        //TODO : ticket.setOffers();
        return ticket;
    }
}
