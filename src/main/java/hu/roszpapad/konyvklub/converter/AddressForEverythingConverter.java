package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverything;
import hu.roszpapad.konyvklub.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressForEverythingConverter implements Converter<Address,AddressForEverything> {

    private final ModelMapper modelMapper;

    @Override
    public AddressForEverything toDTO(Address entity) {
        return modelMapper.map(entity,AddressForEverything.class);
    }

    @Override
    public Address toEntity(AddressForEverything dto) {
        return modelMapper.map(dto, Address.class);
    }
}
