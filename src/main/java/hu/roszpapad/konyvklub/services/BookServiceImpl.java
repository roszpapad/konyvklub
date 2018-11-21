package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.BookCantBeDeletedException;
import hu.roszpapad.konyvklub.exceptions.BookNotFoundException;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book freeBook(Book book) {

        book.setOfferable(true);
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
    }

    @Override
    public Book createBook(Book book) {
        //TODO : book.setOwner("current user");
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Book bookToUpdate = findById(book.getId());

        bookToUpdate.setYearOfPublishing(book.getYearOfPublishing());
        bookToUpdate.setWriter(book.getWriter());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setIsbn(book.getIsbn());
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBook(Long id) {
        Book bookToDelete = findById(id);
        if (!bookToDelete.getOfferable()) {
            throw new BookCantBeDeletedException();
        } else {
            bookRepository.delete(bookToDelete);
        }
    }

    @Override
    public Book makeBookNotOfferable(Book book) {
        book.setOfferable(false);
        return bookRepository.save(book);
    }
}
