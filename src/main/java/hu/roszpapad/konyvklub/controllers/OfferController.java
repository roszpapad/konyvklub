package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.OfferToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeSavedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeUpdatedDTO;
import hu.roszpapad.konyvklub.exceptions.OfferCantBeUpdatedException;
import hu.roszpapad.konyvklub.exceptions.TicketClosedException;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.services.OfferService;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    private final TicketService ticketService;

    private final OfferToBeDisplayedDTOConverter offerToBeDisplayedDTOConverter;

    @GetMapping("/tickets/{ticketId}/offers/{offerId}")
    public ResponseEntity<String> acceptOffer(@PathVariable("ticketId") Long ticketId, @PathVariable("offerId") Long offerId){

        offerService.acceptOffer(ticketService.findById(ticketId),offerService.findById(offerId));

        return ResponseEntity.ok("Offer elfogadva.");
    }


    @PostMapping("/tickets/{ticketId}/offer")
    public ResponseEntity<OfferToBeDisplayedDTO> createOffer(@Valid @RequestBody OfferToBeSavedDTO offerDTO){

        Offer offer = offerService.createOffer(offerDTO);

        return ResponseEntity.ok(offerToBeDisplayedDTOConverter.toDTO(offer));
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<String> rejectOffer(@PathVariable(name = "offerId") Long offerId){
        Offer offer = offerService.rejectOffer(offerService.findById(offerId));
        return ResponseEntity.ok("Offer elutasitva.");
    }

    @DeleteMapping("/offers/{offerId}/delete")
    public ResponseEntity<String> deleteOffer(@PathVariable(name = "offerId") Long offerId){
        offerService.deleteOffer(offerId);
        return ResponseEntity.ok("Offer törölve");
    }

    @PutMapping("/offers/update")
    public ResponseEntity<OfferToBeDisplayedDTO> updateOffer(@RequestBody OfferToBeUpdatedDTO offerDTO){
        Offer offer = offerService.updateOffer(offerDTO);
        return ResponseEntity.ok(offerToBeDisplayedDTOConverter.toDTO(offer));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OfferCantBeUpdatedException.class)
    public ResponseEntity<?> handleUpdateException(Exception exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TicketClosedException.class)
    public ResponseEntity<?> handleTicketClosedException(Exception exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
