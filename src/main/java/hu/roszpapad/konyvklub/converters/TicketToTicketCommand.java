package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketCommand implements Converter<Ticket, TicketCommand> {

    private BookToBookCommand bookToBookCommand;
    private OfferToOfferCommand offerToOfferCommand;
    private UserToUserCommand userToUserCommand;

    public TicketToTicketCommand(BookToBookCommand bookToBookCommand, OfferToOfferCommand offerToOfferCommand, UserToUserCommand userToUserCommand) {
        this.bookToBookCommand = bookToBookCommand;
        this.offerToOfferCommand = offerToOfferCommand;
        this.userToUserCommand = userToUserCommand;
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
        ticketCommand.setSeller(userToUserCommand.convert(ticket.getSeller()));

        if (ticket.getOffers() != null && ticket.getOffers().size() > 0){
            ticket.getOffers()
                    .forEach(offer -> ticketCommand.getOffers().add(offerToOfferCommand.convert(offer)));
        }
        return ticketCommand;
    }
}
