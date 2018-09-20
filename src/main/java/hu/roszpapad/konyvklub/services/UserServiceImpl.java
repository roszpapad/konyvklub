package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    public UserServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

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
