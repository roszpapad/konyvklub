package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTickets();
    Ticket findById(Long id);
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(Long id);
    Ticket removeOfferFromTicket(Ticket ticket, Offer offer);
}
