package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.dtos.TicketDTO;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets",ticketService.getTicketDTOs());

        return "tickets/tickets";
    }

    @GetMapping("/ticket/{ticketId}/offers")
    public String getTicketOffers(@PathVariable Long ticketId, Model model){
        model.addAttribute("ticket",ticketService.getTicketDTOById(ticketId));

        return "tickets/offers";
    }

    @GetMapping("/ticket/{ticketId}/offer/{offerId}")
    public String getOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId, Model model){
        TicketDTO ticketDTO = ticketService.getTicketDTOById(ticketId);
        model.addAttribute("ticket", ticketDTO);
        model.addAttribute("offer",ticketService.getOfferDTO(ticketId,offerId));

        return "tickets/offer";
    }
}
