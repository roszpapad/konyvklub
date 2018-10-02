package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.UserCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class UserToUserCommand implements Converter<User, UserCommand> {

    private AddressToAddressCommand addressConverter;
    private TicketToTicketCommand ticketConverter;

    public UserToUserCommand(AddressToAddressCommand addressConverter, TicketToTicketCommand ticketConverter) {
        this.addressConverter = addressConverter;
        this.ticketConverter = ticketConverter;
    }

    @Override
    public UserCommand convert(User user) {
        if (user == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setAddress(addressConverter.convert(user.getAddress()));
        userCommand.setAdmin(user.getAdmin());
        //userCommand.setBooks();
        userCommand.setFirstName(user.getFirstName());
        userCommand.setLastName(user.getLastName());
        userCommand.setId(user.getId());
        //userCommand.setOffersInInterest();
        userCommand.setTicketsCreated(user.getTicketsCreated().stream().map((Ticket e) -> ticketConverter.convert(e))
                .collect(Collectors.toSet()));
        userCommand.setUserName(user.getUserName());
        userCommand.setPassword(user.getPassword());

        return userCommand;
    }
}
