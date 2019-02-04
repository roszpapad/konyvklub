
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

    private final OfferService offerService;

    private final int NUMBER_OF_WEEKS_ACTIVE = 2;

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
        savableTicket.setEndDate(LocalDateTime.now().plusWeeks(NUMBER_OF_WEEKS_ACTIVE));

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

        List<Offer> offersPending = current.getOffers().stream().filter(offer -> offer.getStatus().equals(Status.PENDING))
                .collect(Collectors.toList());

        offersPending.forEach(offer -> offerService.rejectOffer(offer));
        current.getOffers().forEach(offer -> userService.removeOfferFromUser(offer.getCustomer(), offer));
        userService.removeTicketFromUser(current.getSeller(),current);

        Book currentBook = current.getBookToSell();
        currentBook.setOfferable(true);
        bookRepository.save(currentBook);
        ticketRepository.delete(current);
    }


    @Override
    public Ticket removeOfferFromTicket(Ticket ticket, Offer offer) {
        ticket.getOffers().remove(offer);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> filterTickets(String title, String writer, String city) {
        boolean isFiltered = false;
        List<Ticket> tickets = getTickets();
        List<Ticket> ticketsToReturn = new ArrayList<>();
        if (title != null && !title.isEmpty()) {
            isFiltered = true;
            String lowerTitle = title.toLowerCase();
            ticketsToReturn = tickets.stream()
                    .filter(ticket -> ticket.getBookToSell().getTitle().toLowerCase().contains(lowerTitle))
                    .collect(Collectors.toList());
        }

        if (writer != null && !writer.isEmpty()){
            isFiltered = true;
            String lowerWriter = writer.toLowerCase();
            if (!ticketsToReturn.isEmpty()) {
                ticketsToReturn = ticketsToReturn.stream()
                        .filter(ticket -> ticket.getBookToSell().getWriter().toLowerCase().contains(lowerWriter))
                        .collect(Collectors.toList());
            } else {
                ticketsToReturn = tickets.stream()
                        .filter(ticket -> ticket.getBookToSell().getWriter().toLowerCase().contains(lowerWriter))
                        .collect(Collectors.toList());
            }
        }

        if (city != null && !city.isEmpty()){
            String lowerCity = city.toLowerCase();
            if (isFiltered) {
                ticketsToReturn = ticketsToReturn.stream()
                        .filter(ticket -> ticket.getSeller().getAddress().getCity().toLowerCase().contains(lowerCity))
                        .collect(Collectors.toList());
            } else {
                ticketsToReturn = tickets.stream()
                        .filter(ticket -> ticket.getSeller().getAddress().getCity().toLowerCase().contains(lowerCity))
                        .collect(Collectors.toList());
            }
        }


        return ticketsToReturn;
    }

}

