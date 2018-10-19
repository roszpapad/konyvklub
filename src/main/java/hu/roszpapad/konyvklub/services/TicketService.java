package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayed;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.Optional;

public interface TicketService {

    Iterable<Ticket> getTickets();
    Optional<Ticket> getTicketById(Long id);
    TicketToBeDisplayed getTicketDTOById(Long id);
    Iterable<TicketToBeDisplayed> getTicketDTOs();
    Ticket acceptOffer(Long ticketId, Long offerId);
}
