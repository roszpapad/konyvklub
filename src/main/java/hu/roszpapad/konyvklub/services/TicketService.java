package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferDTO;
import hu.roszpapad.konyvklub.dtos.TicketDTO;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.Optional;

public interface TicketService {

    Iterable<Ticket> getTickets();
    Optional<Ticket> getTicketById(Long id);
    TicketDTO getTicketDTOById(Long id);
    Iterable<TicketDTO> getTicketDTOs();
    Iterable<OfferDTO> getOfferDTOs(Long ticketId);
}
