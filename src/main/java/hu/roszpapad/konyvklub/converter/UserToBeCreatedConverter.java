package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverything;
import hu.roszpapad.konyvklub.dtos.UserToBeCreated;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToBeCreatedConverter implements Converter<User, UserToBeCreated> {

    private final ModelMapper modelMapper;

    private final Converter<Address, AddressForEverything> addressForEverythingConverter;

    @Override
    public UserToBeCreated toDTO(User entity) {
        UserToBeCreated userDTO = new UserToBeCreated();
        userDTO.setAddress(addressForEverythingConverter.toDTO(entity.getAddress()));
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setPassword(entity.getPassword());
        return userDTO;
    }

    @Override
    public User toEntity(UserToBeCreated dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(addressForEverythingConverter.toEntity(dto.getAddress()));
        user.setPassword(dto.getPassword());
        return user;
    }
}
