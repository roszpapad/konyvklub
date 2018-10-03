package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.commands.OfferCommand;
import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.Optional;

public interface TicketService {

    Iterable<Ticket> getTickets();
    Optional<Ticket> getTicketById(Long id);
    TicketCommand getCommandById(Long id);
    Iterable<TicketCommand> getTicketCommands();
    Iterable<OfferCommand> getOfferCommands(Long ticketId);
}
