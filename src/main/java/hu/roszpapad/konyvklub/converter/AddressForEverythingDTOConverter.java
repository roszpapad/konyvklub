package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverythingDTO;
import hu.roszpapad.konyvklub.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressForEverythingDTOConverter {

    private final ModelMapper modelMapper;

    public AddressForEverythingDTO toDTO(Address entity) {
        return modelMapper.map(entity,AddressForEverythingDTO.class);
    }
}
