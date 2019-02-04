package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverythingDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserToBeDisplayedDTOConverter implements Converter<User, UserToBeDisplayedDTO> {

    private final Converter<Address, AddressForEverythingDTO> addressForEverythingConverter;

    @Override
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

    @Override
    public User toEntity(UserToBeDisplayedDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setAddress(addressForEverythingConverter.toEntity(dto.getAddress()));
        user.setEmail(dto.getEmail());
        user.setActive(dto.getActive());
        return user;
    }
}
