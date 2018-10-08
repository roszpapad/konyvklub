package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void addBookToUser(User user, Book book) {
        Set<Book> books = user.getBooks();
        books.add(book);
        user.setBooks(books);
    }

    @Override
    public void deleteBookFromUser(User user, Book book) {
        user.getBooks().remove(book);
        bookRepository.delete(book);
    }
}
