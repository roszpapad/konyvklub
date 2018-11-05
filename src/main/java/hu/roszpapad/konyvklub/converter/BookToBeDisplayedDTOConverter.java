package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookToBeDisplayedDTOConverter implements Converter<Book, BookToBeDisplayedDTO> {

    private final ModelMapper modelMapper;

    @Override
    public BookToBeDisplayedDTO toDTO(Book entity) {
        return modelMapper.map(entity, BookToBeDisplayedDTO.class);
    }

    @Override
    public Book toEntity(BookToBeDisplayedDTO dto) {
        return modelMapper.map(dto, Book.class);
    }
}
