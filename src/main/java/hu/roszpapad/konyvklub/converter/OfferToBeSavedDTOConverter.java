package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeSavedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.services.BookService;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeSavedDTOConverter implements Converter<Offer,OfferToBeSavedDTO> {

    private final BookService bookService;

    private final TicketService ticketService;

    @Override
    public OfferToBeSavedDTO toDTO(Offer entity) {
        OfferToBeSavedDTO offerDTO = new OfferToBeSavedDTO();
        offerDTO.setBookId(entity.getBookToPay().getId());
        offerDTO.setTicketId(entity.getTicket().getId());
        offerDTO.setDescription(entity.getDescription());
        return offerDTO;
    }

    @Override
    public Offer toEntity(OfferToBeSavedDTO dto) {
        Offer offer = new Offer();
        offer.setTicket(ticketService.findById(dto.getTicketId()));
        offer.setDescription(dto.getDescription());
        offer.setBookToPay(bookService.findById(dto.getBookId()));
        return offer;
    }
}
