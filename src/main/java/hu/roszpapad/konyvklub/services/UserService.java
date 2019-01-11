package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.*;

public interface UserService {

    User registerUser(User user);
    User updateUser(User user);
    User findById(Long id);
    User switchActive(Long id);
    RegistrationToken createRegistrationToken(User user, String token);
    User saveUser(User user);

    void addBookToUser(User user, Book book);
    void deleteBookFromUser(User user, Book book);
    User removeTicketFromUser(User user, Ticket ticket);
    User removeOfferFromUser(User user, Offer offer);
    void changeBookBetweenUsers(User user1, Book book1, User user2, Book book2);
}
