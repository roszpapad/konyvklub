package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.UserCommand;
import hu.roszpapad.konyvklub.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private AddressCommandToAddress addressCommandToAddress;
    //private TicketCommandToTicket ticketCommandToTicket;
    private BookCommandToBook bookCommandToBook;
    //private OfferCommandToOffer offerCommandToOffer;


    public UserCommandToUser(AddressCommandToAddress addressCommandToAddress, BookCommandToBook bookCommandToBook) {
        this.addressCommandToAddress = addressCommandToAddress;
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public User convert(UserCommand userCommand) {

        if (userCommand == null) {
            return null;
        }

        final User user = new User();
        user.setAddress(addressCommandToAddress.convert(userCommand.getAddress()));
        user.setAdmin(userCommand.getAdmin());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setId(userCommand.getId());
        user.setUserName(userCommand.getUserName());
        user.setPassword(userCommand.getPassword());
        /*if (userCommand.getTicketsCreated() != null && userCommand.getTicketsCreated().size() > 0){
            userCommand.getTicketsCreated()
                    .forEach(ticketCommand -> user.getTicketsCreated().add(ticketCommandToTicket.convert(ticketCommand)));
        }*/

        if (userCommand.getBooks() != null && userCommand.getBooks().size() > 0){
            userCommand.getBooks()
                    .forEach(bookCommand -> user.getBooks().add(bookCommandToBook.convert(bookCommand)));
        }

        /*if (userCommand.getOffersInInterest() != null && userCommand.getOffersInInterest().size() > 0){
            userCommand.getOffersInInterest()
                    .forEach(offerCommand -> user.getOffersInInterest().add(offerCommandToOffer.convert(offerCommand)));
        }*/

        return user;
    }
}
