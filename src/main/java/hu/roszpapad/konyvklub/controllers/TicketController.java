package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @RequestMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets",ticketService.getTicketDTOs());

        return "tickets/tickets";
    }
}
