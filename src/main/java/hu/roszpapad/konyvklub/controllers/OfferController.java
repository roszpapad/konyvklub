package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeSavedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.services.OfferService;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    private final TicketService ticketService;

    private final Converter<Offer, OfferToBeSavedDTO> offerToBeSavedDTOConverter;

    @GetMapping("/tickets/{ticketId}/offers/{offerId}")
    public ResponseEntity<String> acceptOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId){

        offerService.acceptOffer(ticketService.findById(ticketId),offerService.findById(offerId));

        return ResponseEntity.ok("Offer elfogadva.");
    }


    @PostMapping("/tickets/{ticketId}/offer")
    public String createOffer(@ModelAttribute("newOffer") OfferToBeSavedDTO offerDTO){

        Offer offer = offerService.createOffer(offerToBeSavedDTOConverter.toEntity(offerDTO));

        return "redirect:/tickets/" + offer.getTicket().getId();
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<String> rejectOffer(@PathVariable(name = "offerId") Long offerId){
        Offer offer = offerService.rejectOffer(offerService.findById(offerId));
        return ResponseEntity.ok("Offer elutasitva.");
    }
}
