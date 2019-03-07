package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
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

    private final Converter<Offer, OfferToBeDisplayedDTO> offerToBeDisplayedDTOConverter;

    @GetMapping("/tickets/{ticketId}/offers/{offerId}")
    public ResponseEntity<String> acceptOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId){

        offerService.acceptOffer(ticketService.findById(ticketId),offerService.findById(offerId));

        return ResponseEntity.ok("Offer elfogadva.");
    }


    @PostMapping("/tickets/{ticketId}/offer")
    public ResponseEntity<OfferToBeDisplayedDTO> createOffer(@RequestBody OfferToBeSavedDTO offerDTO){

        Offer offer = offerService.createOffer(offerDTO);

        return ResponseEntity.ok(offerToBeDisplayedDTOConverter.toDTO(offer));
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<String> rejectOffer(@PathVariable(name = "offerId") Long offerId){
        Offer offer = offerService.rejectOffer(offerService.findById(offerId));
        return ResponseEntity.ok("Offer elutasitva.");
    }
}
