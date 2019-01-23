package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Book;

import java.util.List;

public interface BookService {

    Book freeBook(Book book);

    Book findById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);

    Book makeBookNotOfferable(Book book);

    List<Book> getAllBooksByUser(Long userId);

    List<Book> getAllOfferableBooksByUser(Long userId);
}
