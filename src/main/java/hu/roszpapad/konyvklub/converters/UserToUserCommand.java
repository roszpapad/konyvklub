package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.UserCommand;
import hu.roszpapad.konyvklub.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    private AddressToAddressCommand addressToAddressCommand;
    //private TicketToTicketCommand ticketToTicketCommand;
    private BookToBookCommand bookToBookCommand;
    //private OfferToOfferCommand offerToOfferCommand;


    public UserToUserCommand(AddressToAddressCommand addressToAddressCommand, BookToBookCommand bookToBookCommand) {
        this.addressToAddressCommand = addressToAddressCommand;
        this.bookToBookCommand = bookToBookCommand;
    }

    @Override
    public UserCommand convert(User user) {
        if (user == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setAddress(addressToAddressCommand.convert(user.getAddress()));
        userCommand.setAdmin(user.getAdmin());
        userCommand.setFirstName(user.getFirstName());
        userCommand.setLastName(user.getLastName());
        userCommand.setId(user.getId());
        userCommand.setUserName(user.getUserName());
        userCommand.setPassword(user.getPassword());

        /*if (user.getTicketsCreated() != null && user.getTicketsCreated().size() > 0){
            user.getTicketsCreated()
                    .forEach(ticket -> userCommand.getTicketsCreated().add(ticketToTicketCommand.convert(ticket)));
        }*/

        if (user.getBooks() != null && user.getBooks().size() > 0){
            user.getBooks()
                    .forEach(book -> userCommand.getBooks().add(bookToBookCommand.convert(book)));
        }

       /* if (user.getOffersInInterest() != null && user.getOffersInInterest().size() > 0){
            user.getOffersInInterest()
                    .forEach(offer -> userCommand.getOffersInInterest().add(offerToOfferCommand.convert(offer)));
        }*/

        return userCommand;
    }
}
