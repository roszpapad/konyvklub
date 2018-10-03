package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets",ticketService.getTicketCommands());

        return "tickets/tickets";
    }
}
