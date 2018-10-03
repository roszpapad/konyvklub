package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketCommandToTicket implements Converter<TicketCommand, Ticket> {

    private BookCommandToBook bookCommandToBook;
    private OfferCommandToOffer offerCommandToOffer;
    private UserCommandToUser userCommandToUser;

    public TicketCommandToTicket(BookCommandToBook bookCommandToBook, OfferCommandToOffer offerCommandToOffer, UserCommandToUser userCommandToUser) {
        this.bookCommandToBook = bookCommandToBook;
        this.offerCommandToOffer = offerCommandToOffer;
        this.userCommandToUser = userCommandToUser;
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
        ticket.setSeller(userCommandToUser.convert(ticketCommand.getSeller()));

        if (ticketCommand.getOffers() != null && ticketCommand.getOffers().size() > 0){
            ticketCommand.getOffers()
                    .forEach(offerCommand -> ticket.getOffers().add(offerCommandToOffer.convert(offerCommand)));
        }

        return ticket;
    }
}
