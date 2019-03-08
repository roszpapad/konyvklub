package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTickets();
    Ticket findById(Long id);
    Ticket createTicket(TicketToBeCreatedDTO ticketDTO);
    Ticket updateTicket(TicketToBeUpdatedDTO ticket);
    void deleteTicket(Long id, boolean isFromScheduler);
    Ticket removeOfferFromTicket(Ticket ticket, Offer offer);
    List<Ticket> filterTickets(String title, String writer, String city, boolean owned, Long id);
}
