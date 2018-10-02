package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.OfferCommand;
import hu.roszpapad.konyvklub.model.Offer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfferCommandToOffer implements Converter<OfferCommand, Offer> {

    private BookCommandToBook bookCommandToBook;

    public OfferCommandToOffer(BookCommandToBook bookCommandToBook) {
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public Offer convert(OfferCommand offerCommand) {
        if (offerCommand == null) {
           return null;
        }

        final Offer offer = new Offer();
        offer.setId(offerCommand.getId());
        offer.setStatus(offerCommand.getStatus());
        offer.setBookToPay(bookCommandToBook.convert(offerCommand.getBookToPay()));
        return offer;
    }
}
