package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.AddressCommand;
import hu.roszpapad.konyvklub.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address> {

   /* private UserCommandToUser userCommandConverter;

    public AddressCommandToAddress(UserCommandToUser userCommandConverter) {
        this.userCommandConverter = userCommandConverter;
    }
*/
    @Override
    public Address convert(AddressCommand addressCommand) {

        if (addressCommand == null){
            return null;
        }

        final Address address = new Address();
        address.setId(addressCommand.getId());
        address.setCity(addressCommand.getCity());
        address.setNumber(addressCommand.getNumber());
        address.setStreet(addressCommand.getStreet());
        //address.setUser(userCommandConverter.convert(addressCommand.getUser()));
        return address;
    }
}
