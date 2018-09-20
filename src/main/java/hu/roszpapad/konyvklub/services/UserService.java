package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.User;

public interface UserService {

    void addBookToUser(User user, Book book);
    void deleteBookFromUser(User user, Book book);
}
