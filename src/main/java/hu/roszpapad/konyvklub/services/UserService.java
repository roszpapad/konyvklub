package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;

public interface UserService {

    UserToBeCreatedDTO prepareUserForCreation();
    User registerUser(User user);
    Boolean userExists(String email);
    User updateUser(User user);
    User findById(Long id);
    User switchActive(Long id);

    void addBookToUser(User user, Book book);
    void deleteBookFromUser(User user, Book book);
    User removeTicketFromUser(User user, Ticket ticket);
    User removeOfferFromUser(User user, Offer offer);
    void changeBookBetweenUsers(User user1, Book book1, User user2, Book book2);
}
