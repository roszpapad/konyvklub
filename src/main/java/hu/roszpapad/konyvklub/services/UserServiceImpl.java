package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.AddressToBeSavedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.exceptions.UserAlreadyExistsException;
import hu.roszpapad.konyvklub.exceptions.UserNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.AuthorityRepository;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;


    @Override
    public UserToBeCreatedDTO prepareUserForCreation() {
        UserToBeCreatedDTO user = new UserToBeCreatedDTO();
        AddressToBeSavedDTO address = new AddressToBeSavedDTO();
        user.setAddress(address);
        return user;
    }

    @Override
    public User registerUser(User user) {
        if (!userExists(user.getEmail())){
            User registeredUser = new User();
            registeredUser.setAddress(user.getAddress());
            registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
            registeredUser.setLastName(user.getLastName());
            registeredUser.setFirstName(user.getFirstName());
            registeredUser.setEmail(user.getEmail());
            registeredUser.setUsername(user.getUsername());

            Authority auth = authorityRepository.findAuthorityByAuthority("ROLE_KONYVKLUB_USER").get();
            user.setAuthorities(Arrays.asList(auth));
            user.setEnabled(false);
            User savedUser = userRepository.save(registeredUser);
            return savedUser;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public Boolean userExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public User updateUser(User user) {
        User current = findById(user.getId());

        current.setFirstName(user.getFirstName());
        current.setLastName(user.getLastName());
        current.setEmail(user.getEmail());
        current.setAddress(user.getAddress());
        return userRepository.save(current);
    }

    @Override
    public User findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public User switchActive(Long id) {
        User user = findById(id);
        user.setActive(!user.isActive());
        return userRepository.save(user);
    }

    @Override
    public void addBookToUser(User user, Book book) {

        if (!user.getBooks().contains(book)) {
            user.getBooks().add(book);
            book.setOwner(user);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteBookFromUser(User user, Book book) {
        user.getBooks().remove(book);
        bookRepository.delete(book);
        userRepository.save(user);
    }

    @Override
    public User removeTicketFromUser(User user, Ticket ticket) {
        user.getTicketsCreated().remove(ticket);
        return userRepository.save(user);
    }

    @Override
    public User removeOfferFromUser(User user, Offer offer) {
        user.getOffersInInterest().remove(offer);
        return userRepository.save(user);
    }

    @Override
    public void changeBookBetweenUsers(User user1, Book book1, User user2, Book book2) {
        user1.getBooks().remove(book1);
        user2.getBooks().remove(book2);

        user1.getBooks().add(book2);
        user2.getBooks().add(book1);

        book1.setOwner(user2);
        book2.setOwner(user1);

        bookRepository.save(book1);
        bookRepository.save(book2);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
