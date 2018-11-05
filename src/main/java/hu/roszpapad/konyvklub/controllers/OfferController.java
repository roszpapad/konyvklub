package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdatedDTO;
import hu.roszpapad.konyvklub.exceptions.TicketClosedException;
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

    private final Converter<Offer, OfferToBeSavedOrUpdatedDTO> offerConverter;

    @GetMapping("/ticket/{ticketId}/offer/{offerId}/accept")
    public String acceptOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId){

        offerService.acceptOffer(ticketService.findById(ticketId),offerService.findById(offerId));

        return "redirect:/ticket/" + ticketId + "/offer/" + offerId;
    }

    @GetMapping("/ticket/{ticketId}/offer/new")
    public String newOffer(@PathVariable("ticketId") Long ticketId, Model model){
        Ticket ticket = ticketService.findById(ticketId);

        if (!ticket.isOpen())
            throw new TicketClosedException();

        OfferToBeSavedOrUpdatedDTO offerDTO = new OfferToBeSavedOrUpdatedDTO();
        offerDTO.setTicketId(ticketId);
        //todo offerDTO.setCustomer(currentUser);
        model.addAttribute("offer",offerDTO);
        model.addAttribute("ticket",ticket);
        return "tickets/makeOffer";
    }

    @PostMapping("/ticket/{ticketId}/offer")
    public String createOffer(@ModelAttribute("offer") OfferToBeSavedOrUpdatedDTO offerDTO){

        Offer offer = offerService.createOffer(offerConverter.toEntity(offerDTO));

        return "redirect:/ticket/" + offer.getTicket().getId();
    }
}
