package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdated;
import hu.roszpapad.konyvklub.exceptions.TicketClosedException;
import hu.roszpapad.konyvklub.exceptions.TicketNotFoundException;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.OfferService;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    private final TicketService ticketService;

    @GetMapping("/ticket/{ticketId}/offer/new")
    public String newOffer(@PathVariable("ticketId") Long ticketId, Model model){
        Ticket ticket = ticketService.getTicketById(ticketId).orElseThrow(() -> new TicketNotFoundException());

        if (!ticket.isOpen())
            throw new TicketClosedException();

        OfferToBeSavedOrUpdated offerDTO = new OfferToBeSavedOrUpdated();
        offerDTO.setTicketId(ticketId);
        //todo offerDTO.setCustomer(currentUser);
        model.addAttribute("offer",offerDTO);
        model.addAttribute("ticket",ticket);
        return "tickets/makeOffer";
    }

    @PostMapping("/ticket/{ticketId}/offer")
    public String saveOrUpdateOffer(@ModelAttribute("offer") OfferToBeSavedOrUpdated offerDTO){

        Offer offer = offerService.saveOfferDTO(offerDTO);

        return "redirect:/ticket/" + offer.getTicket().getId();
    }
}
