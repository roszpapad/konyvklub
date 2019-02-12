package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.UserNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.AuthorityRepository;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.RegistrationTokenRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Override
    public User registerUser(User user) {
        User registeredUser = new User();
        registeredUser.setAddress(user.getAddress());
        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registeredUser.setLastName(user.getLastName());
        registeredUser.setFirstName(user.getFirstName());
        registeredUser.setEmail(user.getEmail());
        registeredUser.setUsername(user.getUsername());
        registeredUser.setEnabled(false);
        Authority auth = authorityRepository.findAuthorityByAuthority("ROLE_KONYVKLUB_USER").get();
        registeredUser.setAuthorities(Arrays.asList(auth));
        User savedUser = userRepository.save(registeredUser);
        return savedUser;
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
    public User switchActive(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
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

    @Override
    public RegistrationToken createRegistrationToken(User user, String token) {
        RegistrationToken registrationToken = new RegistrationToken(token,user);
        return registrationTokenRepository.save(registrationToken);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changeProfilePicture(Long userId, String file) {
        User user = findById(userId);
        user.setImage(file);
        saveUser(user);
    }

    @Override
    public String findPicture(Long userId) {
        return findById(userId).getImage();
    }

    @Override
    public List<User> getUsersByUsernameFilter(String username) {
        List<User> users = findAll();
        List<User> usersToReturn = new ArrayList<>();
        if (username != null && !username.equals("")){
            String toFind = username.toLowerCase();
            usersToReturn = users.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(toFind))
                    .collect(Collectors.toList());
        }
        return usersToReturn;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
