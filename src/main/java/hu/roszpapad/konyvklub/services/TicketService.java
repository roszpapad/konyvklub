package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferDTO;
import hu.roszpapad.konyvklub.dtos.TicketDTO;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Iterable<Ticket> getTickets();
    Optional<Ticket> getTicketById(Long id);
    TicketDTO getTicketDTOById(Long id);
    Iterable<TicketDTO> getTicketDTOs();
    List<OfferDTO> getOfferDTOs(Long ticketId);
    OfferDTO getOfferDTO(Long ticketId, Long offerId);
}
