
package hu.roszpapad.konyvklub.controllers;

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

    @GetMapping("/ticket/{ticketId}")
    public String getTicket(@PathVariable Long ticketId, Model model){
        model.addAttribute("ticket",ticketService.getTicketDTOById(ticketId));

        return "tickets/ticket";
    }

    @GetMapping("/ticket/{ticketId}/offer/{offerId}/accept")
    public String acceptOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId){

        ticketService.acceptOffer(ticketId,offerId);

        return "redirect:/ticket/" + ticketId + "/offer/" + offerId;
    }

    /*@GetMapping("/ticket/{ticketId}/offer/new")
    public String newOffer(@PathVariable("ticketId") Long ticketId,Model model){
        OfferDTO offerDTO = new OfferDTO();
        Ticket ticket = ticketService.getTicketById(ticketId).get();
        model.addAttribute("offer",new OfferDTO());

        return "tickets/makeOffer";
    }

    @PostMapping("/offer")
    public String saveOrUpdateOffer(@ModelAttribute("offer") OfferDTO offerDTO){

        return "/ticket/ticketidjaamitkiszamolokmajd/offer/offeridja";
    }*/
}

