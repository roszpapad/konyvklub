package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TicketToBeCreatedDTOConverter implements Converter<Ticket, TicketToBeCreatedDTO> {

    private final BookService bookService;

    @Override
    public TicketToBeCreatedDTO toDTO(Ticket entity) {
        TicketToBeCreatedDTO ticketDTO = new TicketToBeCreatedDTO();
        ticketDTO.setBookId(entity.getBookToSell().getId());
        ticketDTO.setDescription(entity.getDescription());
        return ticketDTO;
    }

    @Override
    public Ticket toEntity(TicketToBeCreatedDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setBookToSell(bookService.findById(dto.getBookId()));
        ticket.setDescription(dto.getDescription());
        return ticket;
    }
}
