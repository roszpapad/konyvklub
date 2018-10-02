package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.core.convert.converter.Converter;

public class TicketToTicketCommand implements Converter<Ticket, TicketCommand> {

    private UserToUserCommand userConverter;

    public TicketToTicketCommand(UserToUserCommand userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public TicketCommand convert(Ticket ticket) {

        if (ticket == null){
            return null;
        }

        final TicketCommand ticketCommand = new TicketCommand();
        ticketCommand.setId(ticket.getId());
        //ticketCommand.setBookToSell();
        ticketCommand.setDescription(ticket.getDescription());
        ticketCommand.setEndDate(ticket.getEndDate());
        //ticketCommand.setOffers();
        ticketCommand.setSeller(userConverter.convert(ticket.getSeller()));
        return ticketCommand;
    }
}
