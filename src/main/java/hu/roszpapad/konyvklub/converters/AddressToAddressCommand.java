package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.AddressCommand;
import hu.roszpapad.konyvklub.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand> {

    /*private UserToUserCommand userConverter;

    public AddressToAddressCommand(UserToUserCommand userConverter) {
        this.userConverter = userConverter;
    }*/

    @Override
    public AddressCommand convert(Address address) {

        if (address == null){
            return null;
        }

        final AddressCommand addressCommand = new AddressCommand();
        addressCommand.setId(address.getId());
        addressCommand.setCity(address.getCity());
        addressCommand.setNumber(address.getNumber());
        addressCommand.setStreet(address.getStreet());
       // addressCommand.setUser(userConverter.convert(address.getUser()));
        return addressCommand;
    }
}
