package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Book;

public interface BookService {

    Book freeBook(Book book);

    Book findById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);

    Book makeBookNotOfferable(Book book);
}
