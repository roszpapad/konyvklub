
package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final Converter<Ticket, TicketToBeCreatedDTO> ticketToBeCreatedDTOConverter;

    private final Converter<Ticket, TicketToBeDisplayedDTO> ticketToBeDisplayedDTOConverter;

    private final Converter<Ticket, TicketToBeUpdatedDTO> ticketToBeUpdatedDTOConverter;

    @GetMapping("/ticket/all")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getTickets();
        List<TicketToBeDisplayedDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> ticketDTOs.add(ticketToBeDisplayedDTOConverter.toDTO(ticket)));
        model.addAttribute("tickets",ticketDTOs);
        return "tickets/tickets";
    }

    @GetMapping("/ticket/{ticketId}")
    public String getTicket(@PathVariable Long ticketId, Model model){
        model.addAttribute("ticket",ticketToBeDisplayedDTOConverter.toDTO(ticketService.findById(ticketId)));

        return "tickets/ticket";
    }

    @GetMapping("/ticket/create")
    public String createTicket(Model model){
        User currentUser = new User();

        model.addAttribute("ticket",new TicketToBeCreatedDTO());
        //TODO : atkonvertalt konyvek - model.addAttribute("books", currentUser.getBooks())
        return "tickets/newTicket";
    }

    @PostMapping("/ticket/create")
    public String createTicket(Model model, @ModelAttribute(name = "ticket") TicketToBeCreatedDTO ticketDTO){
        Ticket ticket = ticketService.createTicket(ticketToBeCreatedDTOConverter.toEntity(ticketDTO));
        model.addAttribute("ticket", ticketToBeDisplayedDTOConverter.toDTO(ticket));
        return "redirect:tickets/ticket";
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
        ticketService.deleteTicket(ticketId);
        return "redirect:ticket/tickets";
    }

}

