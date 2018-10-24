package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.UserToBeCreated;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToBeCreatedConverter implements Converter<User, UserToBeCreated> {

    private final ModelMapper modelMapper;

    @Override
    public UserToBeCreated toDTO(User entity) {
        return modelMapper.map(entity, UserToBeCreated.class);
    }

    @Override
    public User toEntity(UserToBeCreated dto) {
        return modelMapper.map(dto, User.class);
    }
}
