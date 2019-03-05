
package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.*;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.TicketService;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final UserService userService;

    private final Converter<Ticket, TicketToBeCreatedDTO> ticketToBeCreatedDTOConverter;

    private final Converter<Ticket, TicketToBeDisplayedDTO> ticketToBeDisplayedDTOConverter;

    private final Converter<Ticket, TicketToBeUpdatedDTO> ticketToBeUpdatedDTOConverter;

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;

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
    public ResponseEntity<Long> createTicket(@RequestBody TicketToBeCreatedDTO ticketDTO){
        Ticket ticket = ticketService.createTicket(ticketToBeCreatedDTOConverter.toEntity(ticketDTO));
        return ResponseEntity.ok(ticket.getId());
    }

    @GetMapping("/ticket/{ticketId}/update")
    public String updateTicket(Model model, @PathVariable("ticketId") Long ticketId){
        model.addAttribute("ticket",ticketToBeUpdatedDTOConverter.toDTO(ticketService.findById(ticketId)));
        return "tickets/update";
    }

    @PutMapping("/ticket/update")
    public String updateTicket(Model model, @ModelAttribute(name = "ticket") TicketToBeUpdatedDTO ticketDTO){
        Ticket ticket = ticketService.updateTicket(ticketToBeUpdatedDTOConverter.toEntity(ticketDTO));
        model.addAttribute("ticket",ticketToBeDisplayedDTOConverter.toDTO(ticket));
        return "redirect:tickets/ticket";
    }

    @DeleteMapping("/ticket/{ticketId}/delete")
    public String deleteTicket(@PathVariable("ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId, false);
        return "redirect:tickets/all";
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

