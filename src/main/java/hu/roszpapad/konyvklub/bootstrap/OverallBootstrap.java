
package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.dtos.UserDTO;
import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.AddressRepository;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OverallBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private final AddressRepository addressRepository;
    private final BookRepository bookRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Ticket toSave = getTickets();
        ticketRepository.save(toSave);
        ticketRepository.save(getTicketss());
        User user = userRepository.findById(1L).get();
        user.getBooks().forEach(book -> System.out.println(book.getTitle()));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.getBooks().forEach(bookDTO -> System.out.println(bookDTO));
        User newUser = modelMapper.map(userDTO, User.class);
        newUser.getBooks().forEach(book -> System.out.println(book.getTitle()));
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
        seller.addBook(book);

        Book book1 = new Book();
        book1.setTitle("Sotet vilag");
        costumer.addBook(book1);



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


    private Ticket getTicketss(){

        User seller = userRepository.findById(1L).get();

        Ticket ticket = new Ticket();
        ticket.setSeller(seller);
        ticket.setDescription("I WANT to sell this book.");

        return ticket;
    }
}

