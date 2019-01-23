package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeDisplayedDTOConverter implements Converter<Offer, OfferToBeDisplayedDTO> {

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;
    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedDTOConverter;

    @Override
    public OfferToBeDisplayedDTO toDTO(Offer entity) {
        OfferToBeDisplayedDTO offerDTO = new OfferToBeDisplayedDTO();
        offerDTO.setBookToPay(bookToBeDisplayedDTOConverter.toDTO(entity.getBookToPay()));
        offerDTO.setCustomer(userToBeDisplayedDTOConverter.toDTO(entity.getCustomer()));
        offerDTO.setId(entity.getId());
        offerDTO.setStatus(entity.getStatus());
        offerDTO.setTicketId(entity.getTicket().getId());
        offerDTO.setDescription(entity.getDescription());
        return offerDTO;
    }

    @Override
    public Offer toEntity(OfferToBeDisplayedDTO dto) {
        //TODO : we dont need this
        return null;
    }
}
