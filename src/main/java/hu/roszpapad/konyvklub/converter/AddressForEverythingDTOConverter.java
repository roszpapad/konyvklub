package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverythingDTO;
import hu.roszpapad.konyvklub.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressForEverythingDTOConverter implements Converter<Address,AddressForEverythingDTO> {

    private final ModelMapper modelMapper;

    @Override
    public AddressForEverythingDTO toDTO(Address entity) {
        return modelMapper.map(entity,AddressForEverythingDTO.class);
    }

    @Override
    public Address toEntity(AddressForEverythingDTO dto) {
        return modelMapper.map(dto, Address.class);
    }
}
