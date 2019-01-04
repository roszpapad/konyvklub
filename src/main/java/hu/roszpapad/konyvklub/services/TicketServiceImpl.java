
package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.TicketNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final UserService userService;

    private final BookRepository bookRepository;

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final int NUMBER_OF_MONTHS_ACTIVE = 1;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException());
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        Ticket savableTicket = new Ticket();
        savableTicket.setEndDate(LocalDateTime.now().plusMonths(NUMBER_OF_MONTHS_ACTIVE));
        savableTicket.setOpen(true);

        Book book = ticket.getBookToSell();
        User seller = book.getOwner();
        book.setOfferable(false);
        savableTicket.setBookToSell(book);
        savableTicket.setSeller(seller);
        savableTicket.setDescription(ticket.getDescription());
        Ticket savedTicket = ticketRepository.save(savableTicket);
        seller.getTicketsCreated().add(savedTicket);
        userRepository.save(seller);
        return savedTicket;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket current = findById(ticket.getId());

        current.setDescription(ticket.getDescription());

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket current = findById(id);
        List<Book> booksToBeFreed = new ArrayList<>();

        List<Offer> offersPending = current.getOffers().stream().filter(offer -> offer.getStatus().equals(Status.PENDING))
                .collect(Collectors.toList());
        offersPending.forEach(offer -> booksToBeFreed.add(offer.getBookToPay()));
        booksToBeFreed.add(current.getBookToSell());
        current.getOffers().forEach(offer -> userService.removeOfferFromUser(offer.getCustomer(), offer));
        userService.removeTicketFromUser(current.getSeller(),current);
        booksToBeFreed.forEach(book -> {
            book.setOfferable(true);
            bookRepository.save(book);
        });
        ticketRepository.delete(current);
    }


    @Override
    public Ticket removeOfferFromTicket(Ticket ticket, Offer offer) {
        ticket.getOffers().remove(offer);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> filterTickets(String title, String writer) {
        List<Ticket> tickets;
        if (title != null && !title.isEmpty()){
            tickets = ticketRepository.findByBookToSellTitle(title);
        } else {
            tickets = getTickets();
        }

        if (writer != null && !writer.isEmpty()){
            tickets = tickets.stream()
                    .filter(t -> t.getBookToSell().getWriter().equals(writer))
                    .collect(Collectors.toList());
        }
        return tickets;
    }
}

