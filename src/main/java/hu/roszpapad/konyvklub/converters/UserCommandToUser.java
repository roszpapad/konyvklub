package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.commands.UserCommand;
import hu.roszpapad.konyvklub.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private AddressCommandToAddress addressCommandConverter;
    private TicketCommandToTicket ticketCommandConverter;

    public UserCommandToUser(AddressCommandToAddress addressCommandConverter, TicketCommandToTicket ticketCommandConverter) {
        this.addressCommandConverter = addressCommandConverter;
        this.ticketCommandConverter = ticketCommandConverter;
    }

    @Override
    public User convert(UserCommand userCommand) {

        if (userCommand == null) {
            return null;
        }

        final User user = new User();
        user.setAddress(addressCommandConverter.convert(userCommand.getAddress()));
        user.setAdmin(userCommand.getAdmin());
        //user.setBooks();
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setId(userCommand.getId());
        //user.setOffersInInterest();
        user.setTicketsCreated(userCommand.getTicketsCreated().stream().map((TicketCommand e) -> ticketCommandConverter.convert(e))
                .collect(Collectors.toSet()));
        user.setUserName(userCommand.getUserName());
        user.setPassword(userCommand.getPassword());

        return user;
    }
}
