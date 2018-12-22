package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressToBeSavedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToBeCreatedDTOConverter implements Converter<User, UserToBeCreatedDTO> {

    private final ModelMapper modelMapper;

    private final Converter<Address, AddressToBeSavedDTO> addressToBeSavedDTOConverter;

    @Override
    public UserToBeCreatedDTO toDTO(User entity) {
        UserToBeCreatedDTO userDTO = new UserToBeCreatedDTO();
        userDTO.setAddress(addressToBeSavedDTOConverter.toDTO(entity.getAddress()));
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setUsername(entity.getUsername());
        return userDTO;
    }

    @Override
    public User toEntity(UserToBeCreatedDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(addressToBeSavedDTOConverter.toEntity(dto.getAddress()));
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        return user;
    }
}
