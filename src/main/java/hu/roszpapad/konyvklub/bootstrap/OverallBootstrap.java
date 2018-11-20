
package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OverallBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private final BookRepository bookRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Ticket> toSave = getTickets();
        getOffer();
        getOffer1();
        Offer offer = offerRepository.findById(1L).get();
        Offer offer1 = offerRepository.findById(2L).get();
        toSave.get(0).getOffers().add(offer);
        toSave.get(0).getOffers().add(offer1);
        offer.setTicket(toSave.get(0));
        offer1.setTicket(toSave.get(0));
        offer.setStatus(Status.ACCEPTED);
        ticketRepository.saveAll(toSave);
    }

    private List<Ticket> getTickets(){
        List<Ticket> tickets = new ArrayList<>();
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
        seller.setUsername("lacika");
        seller.setEmail("lola@freemail.hu");

        User costumer = new User();
        costumer.setFirstName("Zita");
        costumer.setLastName("Marias");
        costumer.setPassword("kima");
        costumer.setUsername("Zituka");
        costumer.setEmail("kima@freemail.hu");

        seller.setAddress(address);

        costumer.setAddress(address1);

        Book book = new Book();
        book.setTitle("Gyuruk ura");
        seller.addBook(book);

        Book book1 = new Book();
        book1.setTitle("Sotet vilag");
        costumer.addBook(book1);

        Book book2 = new Book();
        book2.setTitle("Gyuruk ura 2");
        book2.setWriter("Kovacs Pal");
        book2.setIsbn("12354657684");
        book2.setPublisher("Konyv Kiado");
        book2.setYearOfPublishing(2009);
        seller.addBook(book2);

        Book book3 = new Book();
        book3.setTitle("Gyuruk ura 3");
        seller.addBook(book3);

        Book book4 = new Book();
        book4.setTitle("Gyuruk ura 4");
        seller.addBook(book4);

        Book book5 = new Book();
        book5.setTitle("Gyuruk ura 5");
        seller.addBook(book5);


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
        tickets.add(ticket);

        Ticket ticket1 = new Ticket();
        ticket1.setSeller(seller);
        ticket1.setBookToSell(book2);
        ticket1.setDescription("I want to sell this book.");
        tickets.add(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setSeller(seller);
        ticket2.setBookToSell(book3);
        ticket2.setDescription("I want to sell this book.");
        tickets.add(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setSeller(seller);
        ticket3.setBookToSell(book4);
        ticket3.setDescription("I want to sell this book.");
        tickets.add(ticket3);

        Ticket ticket4 = new Ticket();
        ticket4.setSeller(seller);
        ticket4.setBookToSell(book5);
        ticket4.setDescription("I want to sell this book.");
        tickets.add(ticket4);

        return tickets;
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
        User user = userRepository.findById(2L).get();
        Book book = bookRepository.findById(2L).get();
        offer.setCustomer(user);
        offer.setBookToPay(book);
        offer.setDescription("Az alabbi konyv nagyon erdekes, es pont az altalad leirt kalandokat igeri." +
                " Higgy nekem, nem fogod megbanni!!");
        user.getOffersInInterest().add(offer);
        book.setOfferable(false);
        Offer offerSaved = offerRepository.save(offer);
        bookRepository.save(book);
        userRepository.save(user);
        return offerSaved;
    }

    private Offer getOffer1(){
        Offer offer = new Offer();
        User user = userRepository.findById(2L).get();
        Book book = bookRepository.findById(2L).get();
        offer.setCustomer(user);
        offer.setBookToPay(book);
        offer.setDescription("Az alabbi konyv nagyon erdekes, es pont az altalad leirt kalandokat igeri." +
                " Higgy nekem, nem fogod megbanni!!");
        user.getOffersInInterest().add(offer);
        book.setOfferable(false);
        Offer offerSaved = offerRepository.save(offer);
        bookRepository.save(book);
        userRepository.save(user);
        return offerSaved;
    }
}

