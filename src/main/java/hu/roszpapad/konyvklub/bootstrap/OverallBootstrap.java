
package hu.roszpapad.konyvklub.bootstrap;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class OverallBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private final BookRepository bookRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Book book1 = bookRepository.findById(1L).get();
        Book book2 = bookRepository.findById(2L).get();
        Book book3 = bookRepository.findById(3L).get();

        User user1 = userRepository.findById(1L).get();
        User user2 = userRepository.findById(2L).get();

        Ticket ticket1 = ticketRepository.findById(1L).get();
        Ticket ticket2 = ticketRepository.findById(2L).get();

        user1.getBooks().add(book1);
        user1.getBooks().add(book3);
        user2.getBooks().add(book2);


        ticket1.setBookToSell(book1);
        ticket1.setSeller(user1);
        ticket2.setBookToSell(book3);
        ticket2.setSeller(user1);

        userRepository.save(user1);
        userRepository.save(user2);

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
    }
}