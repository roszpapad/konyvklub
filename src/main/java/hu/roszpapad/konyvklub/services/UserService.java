package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.*;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    User updateUser(User user);
    User findById(Long id);
    User switchActive(Long id);
    RegistrationToken createRegistrationToken(User user, String token);
    User saveUser(User user);
    List<User> findAll();

    void addBookToUser(User user, Book book);
    void deleteBookFromUser(User user, Book book);
    User removeTicketFromUser(User user, Ticket ticket);
    User removeOfferFromUser(User user, Offer offer);
    void changeBookBetweenUsers(User user1, Book book1, User user2, Book book2);
    void changeProfilePicture(Long userId, String file);

    String findPicture(Long userId);
    List<User> getUsersByUsernameFilter(String username);
}
