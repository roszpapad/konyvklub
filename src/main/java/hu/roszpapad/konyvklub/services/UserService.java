package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.*;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    User updateUser(User user);
    User findById(Long id);
    User switchActive(String username);
    RegistrationToken createRegistrationToken(User user, String token);
    ChangePasswordToken createChangePasswordToken(User user, String token);
    String changePassword(String token, String password);
    void sendChangePasswordEmail(String username);
    User saveUser(User user);
    List<User> findAll();
    boolean isActive(String username);

    void addBookToUser(User user, Book book);
    void deleteBookFromUser(User user, Book book);
    User removeTicketFromUser(User user, Ticket ticket);
    User removeOfferFromUser(User user, Offer offer);
    void changeBookBetweenUsers(User user1, Book book1, User user2, Book book2);
    void changeProfilePicture(Long userId, String file);
    List<Offer> getUserOffers(Long id);
    List<Ticket> getUserTickets(Long id);

    String findPicture(Long userId);
    String findPictureByUsername(String username);
    List<User> getUsersByUsernameFilter(String username);
}
