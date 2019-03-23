package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserToBeDisplayedDTOConverter {

    private final AddressForEverythingDTOConverter addressForEverythingConverter;

    public UserToBeDisplayedDTO toDTO(User entity) {
        UserToBeDisplayedDTO userDTO = new UserToBeDisplayedDTO();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setUsername(entity.getUsername());
        userDTO.setLastName(entity.getLastName());
        userDTO.setAddress(addressForEverythingConverter.toDTO(entity.getAddress()));
        userDTO.setActive(entity.isActive());
        return userDTO;
    }

}
