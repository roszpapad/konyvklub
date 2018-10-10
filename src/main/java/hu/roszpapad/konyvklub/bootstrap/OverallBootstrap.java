
package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.*;
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
    private final OfferRepository offerRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Ticket toSave = getTickets();
        ticketRepository.save(toSave);
        ticketRepository.save(getTicketss());
        Ticket ticket = ticketRepository.findById(1L).get();
        ticket.addOffer(getOffer());
        ticketRepository.save(ticket);
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

    private Offer getOffer(){
        Offer offer = new Offer();
        offer.setCustomer(userRepository.findById(2L).get());
        offer.setBookToPay(bookRepository.findById(2L).get());
        return offer;
    }
}

