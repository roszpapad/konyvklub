
package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.commands.UserCommand;
import hu.roszpapad.konyvklub.converters.UserCommandToUser;
import hu.roszpapad.konyvklub.converters.UserToUserCommand;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.AddressRepository;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OverallBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AddressRepository addressRepository;
    private BookRepository bookRepository;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private UserService userService;
    private UserToUserCommand userToUserCommand;
    private UserCommandToUser userCommandToUser;

    public OverallBootstrap(AddressRepository addressRepository, BookRepository bookRepository, TicketRepository ticketRepository, UserRepository userRepository, UserService userService, UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser) {
        this.addressRepository = addressRepository;
        this.bookRepository = bookRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Ticket toSave = getTickets();
        ticketRepository.save(toSave);
        User user = userRepository.findById(1L).get();
        UserCommand userCommand = userToUserCommand.convert(user);
        User newUser = userCommandToUser.convert(userCommand);
        userRepository.save(newUser);
    }

    private Ticket getTickets(){
        Address address = new Address();
        address.setCity("Debrecen");
        address.setStreet("Petofi");
        address.setNumber("12");

        Address address1= new Address();
        address1.setCity("Debrecen");
        address1.setStreet("Kosztolanyi");
        address1.setNumber("14");


        User seller = new User();
        seller.setFirstName("Laszlo");
        seller.setLastName("Kovacs");
        seller.setPassword("lola");
        seller.setUserName("kola");

        User costumer = new User();
        costumer.setFirstName("Zita");
        costumer.setLastName("Marias");
        costumer.setPassword("kima");
        costumer.setUserName("tibo");

        seller.setAddress(address);

        costumer.setAddress(address1);

        Book book = new Book();
        book.setTitle("Gyuruk ura");
        userService.addBookToUser(seller,book);

        Book book1 = new Book();
        book1.setTitle("Sotet vilag");
        userService.addBookToUser(costumer,book1);



        userRepository.save(seller);
        userRepository.save(costumer);
        //addressRepository.save(address);
        //addressRepository.save(address1);
       // bookRepository.save(book);
        //bookRepository.save(book1);

        Ticket ticket = new Ticket();
        ticket.setSeller(seller);
        ticket.setBookToSell(book);
        ticket.setDescription("I want to sell this book.");

        return ticket;
    }
}

