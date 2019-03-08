package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeUpdatedDTO;
import hu.roszpapad.konyvklub.exceptions.UserNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public User registerUser(UserToBeCreatedDTO user) {
        User registeredUser = new User();
        Address address = new Address();
        address.setCity(user.getAddress().getCity());
        if (user.getAddress().getNumber() != null){
           address.setNumber(user.getAddress().getNumber());
        }
        if (user.getAddress().getStreet() != null){
            address.setStreet(user.getAddress().getStreet());
        }
        registeredUser.setAddress(address);
        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registeredUser.setLastName(user.getLastName());
        registeredUser.setFirstName(user.getFirstName());
        registeredUser.setEmail(user.getEmail());
        registeredUser.setUsername(user.getUsername());
        registeredUser.setEnabled(false);
        Authority auth = authorityRepository.findAuthorityByAuthority("ROLE_KONYVKLUB_USER").get();
        registeredUser.setAuthorities(Arrays.asList(auth));
        return userRepository.save(registeredUser);
    }

    @Override
    public User updateUser(UserToBeUpdatedDTO user) {
        User current = findById(user.getId());

        current.setFirstName(user.getFirstName());
        current.setLastName(user.getLastName());
        current.getAddress().setCity(user.getAddress().getCity());
        if (user.getAddress().getNumber() != null){
            current.getAddress().setNumber(user.getAddress().getNumber());
        }
        if (user.getAddress().getStreet() != null){
            current.getAddress().setStreet(user.getAddress().getStreet());
        }
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
    public ChangePasswordToken createChangePasswordToken(User user, String token) {
        ChangePasswordToken changePasswordToken = new ChangePasswordToken(token,user);
        return changePasswordTokenRepository.save(changePasswordToken);
    }

    @Override
    public void sendChangePasswordEmail(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        String token = UUID.randomUUID().toString();
        ChangePasswordToken changePasswordToken = createChangePasswordToken(user,token);
        String recipientAddress = changePasswordToken.getUser().getEmail();
        String subject = "Jelszó csere - konyvklub";
        String confirmationUrl
                = "/changePassword?token=" + changePasswordToken.getToken();
        String message = "Kattintson a következő linkre a jelszava cseréjéhez.";

        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(recipientAddress));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(message + "<br/>"
                        + "<a href='http://localhost:4200" + confirmationUrl +  "'>Jelszó cseréhez kattintson ide!</a>","UTF-8","html");
            }
        };
        javaMailSender.send(mimeMessagePreparator);
    }


    @Override
    public String changePassword(String token, String password) {
        Optional<ChangePasswordToken> changePasswordTokenOptional = changePasswordTokenRepository.findByToken(token);
        if (!changePasswordTokenOptional.isPresent()){
            return "notFound";
        }
        ChangePasswordToken changePasswordToken = changePasswordTokenOptional.get();
        if (changePasswordToken.getExpiryDate().isBefore(LocalDateTime.now())){
            return "expired";
        }
        User user = changePasswordToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "success";
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

    @Override
    public boolean isActive(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        return user.isActive();
    }

    @Override
    public List<Offer> getUserOffers(Long id) {
        User user = findById(id);
        return user.getOffersInInterest();
    }

    @Override
    public List<Ticket> getUserTickets(Long id) {
        User user = findById(id);
        return user.getTicketsCreated();
    }

    @Override
    public String findPictureByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        return user.getImage();
    }

    @Override
    public Address getAddressByUserId(Long id) {
        User user = findById(id);
        return user.getAddress();
    }
}
