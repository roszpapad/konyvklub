package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.BookToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookToBeCreatedDTOConverter implements Converter<Book, BookToBeCreatedDTO>{

    private final ModelMapper modelMapper;

    @Override
    public BookToBeCreatedDTO toDTO(Book entity) {
        return modelMapper.map(entity,BookToBeCreatedDTO.class);
    }

    @Override
    public Book toEntity(BookToBeCreatedDTO dto) {
        return modelMapper.map(dto, Book.class);
    }
}
