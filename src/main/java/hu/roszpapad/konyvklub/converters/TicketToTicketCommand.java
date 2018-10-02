package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketCommand implements Converter<Ticket, TicketCommand> {

    private BookToBookCommand bookToBookCommand;

    public TicketToTicketCommand(BookToBookCommand bookToBookCommand) {
        this.bookToBookCommand = bookToBookCommand;
    }

    @Override
    public TicketCommand convert(Ticket ticket) {

        if (ticket == null){
            return null;
        }

        final TicketCommand ticketCommand = new TicketCommand();
        ticketCommand.setId(ticket.getId());
        ticketCommand.setBookToSell(bookToBookCommand.convert(ticket.getBookToSell()));
        ticketCommand.setDescription(ticket.getDescription());
        ticketCommand.setEndDate(ticket.getEndDate());
        //ticketCommand.setOffers();
        //ticketCommand.setSeller(userConverter.convert(ticket.getSeller()));
        return ticketCommand;
    }
}
