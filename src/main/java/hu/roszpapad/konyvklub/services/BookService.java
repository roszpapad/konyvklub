package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.BookToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Book;

import java.util.List;

public interface BookService {

    Book findById(Long id);

    Book createBook(BookToBeCreatedDTO book);

    void deleteBook(Long id);

    List<Book> getAllBooksByUser(Long userId);

    List<Book> getAllOfferableBooksByUser(Long userId);
}
