package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.AddressForEverythingDTO;
import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedWithBooksDTO;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToBeDisplayedWithBooksDTOConverter implements Converter<User, UserToBeDisplayedWithBooksDTO>{

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;
    private final Converter<Address, AddressForEverythingDTO> addressForEverythingDTOConverter;

    @Override
    public UserToBeDisplayedWithBooksDTO toDTO(User entity) {
        UserToBeDisplayedWithBooksDTO userDTO = new UserToBeDisplayedWithBooksDTO();
        userDTO.setAddress(addressForEverythingDTOConverter.toDTO(entity.getAddress()));
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        entity.getBooks().forEach(book -> userDTO.getBooks().add(bookToBeDisplayedDTOConverter.toDTO(book)));
        return userDTO;
    }

    @Override
    public User toEntity(UserToBeDisplayedWithBooksDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setAddress(addressForEverythingDTOConverter.toEntity(dto.getAddress()));
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setId(dto.getId());
        dto.getBooks().forEach(bookDTO -> user.getBooks().add(bookToBeDisplayedDTOConverter.toEntity(bookDTO)));
        return user;
    }
}
