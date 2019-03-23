
package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.TicketToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeUpdatedDTO;
import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final OfferService offerService;

    private final NotificationService notificationService;

    private final int NUMBER_OF_WEEKS_ACTIVE = 2;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Ticket.class));
    }

    @Override
    public Ticket createTicket(TicketToBeCreatedDTO ticketDTO) {
        Ticket savableTicket = new Ticket();
        Book book = bookRepository.findById(ticketDTO.getBookId()).orElseThrow(() -> new NotFoundException(Book.class));
        savableTicket.setEndDate(LocalDateTime.now().plusWeeks(NUMBER_OF_WEEKS_ACTIVE));

        User seller = book.getOwner();
        book.setOfferable(false);
        savableTicket.setBookToSell(book);
        savableTicket.setSeller(seller);
        savableTicket.setDescription(ticketDTO.getDescription());
        Ticket savedTicket = ticketRepository.save(savableTicket);
        seller.getTicketsCreated().add(savedTicket);
        userService.saveUser(seller);
        return savedTicket;
    }

    @Override
    public Ticket updateTicket(TicketToBeUpdatedDTO ticket) {
        Ticket current = findById(ticket.getId());

        current.setDescription(ticket.getDescription());

        return ticketRepository.save(current);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id, boolean isFromScheduler) {
        Ticket current = findById(id);

        List<Offer> offersPending = current.getOffers().stream().filter(offer -> offer.getStatus().equals(Status.PENDING))
                .collect(Collectors.toList());

        offersPending.forEach(offer -> offerService.rejectOffer(offer));
        current.getOffers().forEach(offer -> userService.removeOfferFromUser(offer.getCustomer(), offer));

        if (isFromScheduler){
            notificationService.createExpiredTicketNotification(current);
        }

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
    public List<Ticket> filterTickets(String title, String writer, String city, boolean owned, Long id) {

        boolean isFiltered = false;

        List<Ticket> tickets = getTickets();
        List<Ticket> ticketsToReturn = new ArrayList<>();

        if (owned) {
            tickets = tickets.stream()
                    .filter(ticket -> ticket.getSeller().getId() == id)
                    .collect(Collectors.toList());
            ticketsToReturn = tickets;
        }

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

