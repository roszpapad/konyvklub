package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressToBeSavedDTO;
import hu.roszpapad.konyvklub.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressToBeSavedDTOConverter implements Converter<Address, AddressToBeSavedDTO>{

    private final ModelMapper modelMapper;

    @Override
    public AddressToBeSavedDTO toDTO(Address entity) {
        return modelMapper.map(entity, AddressToBeSavedDTO.class);
    }

    @Override
    public Address toEntity(AddressToBeSavedDTO dto) {
        return modelMapper.map(dto, Address.class);
    }
}
