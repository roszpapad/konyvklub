package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.AddressRepository;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class OverallBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AddressRepository addressRepository;
    private BookRepository bookRepository;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    public OverallBootstrap(AddressRepository addressRepository, BookRepository bookRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.bookRepository = bookRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Ticket toSave = getTickets();
        ticketRepository.save(toSave);
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

        userRepository.save(seller);
        userRepository.save(costumer);
        addressRepository.save(address);
        addressRepository.save(address1);

        Book book = new Book();
        book.setTitle("Gyuruk ura");
        bookRepository.save(book);

        Book book1 = new Book();
        book1.setTitle("Sotet vilag");
        bookRepository.save(book1);

        Ticket ticket = new Ticket();
        ticket.setSeller(seller);
        ticket.setCustomer(costumer);
        ticket.setBookToPay(book1);
        ticket.setBookToSell(book);

        return ticket;
    }
}
