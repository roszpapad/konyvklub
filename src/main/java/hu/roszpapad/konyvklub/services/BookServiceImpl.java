package hu.roszpapad.konyvklub.services;

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
}
