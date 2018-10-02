package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketCommandToTicket implements Converter<TicketCommand, Ticket> {

    private BookCommandToBook bookCommandToBook;

    public TicketCommandToTicket(BookCommandToBook bookCommandToBook) {
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public Ticket convert(TicketCommand ticketCommand) {

        if (ticketCommand == null){
            return null;
        }

        final Ticket ticket = new Ticket();
        ticket.setId(ticketCommand.getId());
        ticket.setBookToSell(bookCommandToBook.convert(ticketCommand.getBookToSell()));
        ticket.setDescription(ticketCommand.getDescription());
        ticket.setEndDate(ticketCommand.getEndDate());
        //ticket.setOffers();
        //ticket.setSeller(userCommandConverter.convert(ticketCommand.getSeller()));
        return ticket;
    }
}
