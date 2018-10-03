package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.OfferCommand;
import hu.roszpapad.konyvklub.model.Offer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfferToOfferCommand implements Converter<Offer, OfferCommand>{

    private BookToBookCommand bookToBookCommand;

    public OfferToOfferCommand(BookToBookCommand bookToBookCommand) {
        this.bookToBookCommand = bookToBookCommand;
    }

    @Override
    public OfferCommand convert(Offer offer) {
        if (offer == null){
            return null;
        }

        final OfferCommand offerCommand = new OfferCommand();
        offerCommand.setId(offer.getId());
        offerCommand.setBookToPay(bookToBookCommand.convert(offer.getBookToPay()));
        offerCommand.setStatus(offer.getStatus());

        if (offer.getCustomer().getId() != null){
            offerCommand.setCustomerId(offer.getCustomer().getId());
        }

        if (offer.getTicket().getId() != null){
            offerCommand.setTicketId(offer.getTicket().getId());
        }

        return offerCommand;
    }
}
