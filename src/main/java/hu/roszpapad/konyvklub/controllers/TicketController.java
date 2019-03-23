
package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.TicketToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final TicketToBeDisplayedDTOConverter ticketToBeDisplayedDTOConverter;

    @GetMapping("/tickets/all")
    public ResponseEntity<List<TicketToBeDisplayedDTO>> getAllTickets() {
        List<Ticket> tickets = ticketService.getTickets();
        List<TicketToBeDisplayedDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> ticketDTOs.add(ticketToBeDisplayedDTOConverter.toDTO(ticket)));
        return ResponseEntity.ok(ticketDTOs);
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<TicketToBeDisplayedDTO> getTicket(@PathVariable Long ticketId){
        TicketToBeDisplayedDTO ticketDTO = ticketToBeDisplayedDTOConverter.toDTO(ticketService.findById(ticketId));
        return ResponseEntity.ok(ticketDTO);
    }

    @PostMapping("/tickets/new")
    public ResponseEntity<Long> createTicket(@Valid @RequestBody TicketToBeCreatedDTO ticketDTO){
        Ticket ticket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(ticket.getId());
    }

    @DeleteMapping("/tickets/{ticketId}/delete")
    public ResponseEntity<String> deleteTicket(@PathVariable("ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId, false);
        return ResponseEntity.ok("Ticket törölve.");
    }

    @PutMapping("/tickets/update")
    public ResponseEntity<TicketToBeDisplayedDTO> updateTicket(@RequestBody TicketToBeUpdatedDTO ticketDTO){
        Ticket ticket = ticketService.updateTicket(ticketDTO);
        return ResponseEntity.ok(ticketToBeDisplayedDTOConverter.toDTO(ticket));
    }

    @GetMapping("/tickets/filter")
    public ResponseEntity<List<TicketToBeDisplayedDTO>> findTickets(@PathParam(value = "title") String title,
                                                                    @PathParam(value = "writer") String writer,
                                                                    @PathParam(value = "city") String city,
                                                                    @PathParam(value = "owned") boolean owned,
                                                                    @PathParam(value = "id") Long id){
        List<Ticket> tickets = ticketService.filterTickets(title, writer,city, owned, id);
        List<TicketToBeDisplayedDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> ticketDTOs.add(ticketToBeDisplayedDTOConverter.toDTO(ticket)));
        return ResponseEntity.ok(ticketDTOs);
    }


}

