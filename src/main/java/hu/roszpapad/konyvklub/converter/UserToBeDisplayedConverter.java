package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverything;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayed;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserToBeDisplayedConverter implements Converter<User, UserToBeDisplayed> {

    private final ModelMapper modelMapper;

    private final Converter<Address, AddressForEverything> addressForEverythingConverter;

    @Override
    public UserToBeDisplayed toDTO(User entity) {
        UserToBeDisplayed userDTO = new UserToBeDisplayed();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setAddress(addressForEverythingConverter.toDTO(entity.getAddress()));
        return userDTO;
    }

    @Override
    public User toEntity(UserToBeDisplayed dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(addressForEverythingConverter.toEntity(dto.getAddress()));
        user.setEmail(dto.getEmail());
        return user;
    }
}
