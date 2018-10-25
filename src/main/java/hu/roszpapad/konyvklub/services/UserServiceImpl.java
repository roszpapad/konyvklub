package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.AddressForEverything;
import hu.roszpapad.konyvklub.dtos.UserToBeCreated;
import hu.roszpapad.konyvklub.exceptions.UserAlreadyExistsException;
import hu.roszpapad.konyvklub.exceptions.UserNotFoundException;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserToBeCreated prepareUserForCreation() {
        UserToBeCreated user = new UserToBeCreated();
        AddressForEverything address = new AddressForEverything();
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
            return userRepository.save(registeredUser);
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

        current.setId(user.getId());
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
    public User switchActive(User user) {

        user.setActive(!user.isActive());
        return userRepository.save(user);
    }

    @Override
    public void addBookToUser(User user, Book book) {
        List<Book> books = user.getBooks();
        books.add(book);
        user.setBooks(books);
    }

    @Override
    public void deleteBookFromUser(User user, Book book) {
        user.getBooks().remove(book);
        bookRepository.delete(book);
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

}
