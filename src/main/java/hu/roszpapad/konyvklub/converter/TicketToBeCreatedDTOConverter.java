package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TicketToBeCreatedDTOConverter implements Converter<Ticket, TicketToBeCreatedDTO> {

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;

    @Override
    public TicketToBeCreatedDTO toDTO(Ticket entity) {
        TicketToBeCreatedDTO ticketDTO = new TicketToBeCreatedDTO();
        ticketDTO.setBookToSell(bookToBeDisplayedDTOConverter.toDTO(entity.getBookToSell()));
        ticketDTO.setDescription(entity.getDescription());
        return ticketDTO;
    }

    @Override
    public Ticket toEntity(TicketToBeCreatedDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setBookToSell(bookToBeDisplayedDTOConverter.toEntity(dto.getBookToSell()));
        ticket.setDescription(dto.getDescription());
        return ticket;
    }
}
