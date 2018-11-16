
package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayedDTO;
import hu.roszpapad.konyvklub.exceptions.TicketNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final UserService userService;

    private final BookService bookService;

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final Converter<Ticket,TicketToBeDisplayedDTO> diplayableTicketConverter;

    private final Converter<Offer,OfferToBeDisplayedDTO> displayableOfferConverter;

    private final Long NUMBER_OF_MONTHS_ACTIVE = 3L;

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

        Book book = bookService.findById(ticket.getBookToSell().getId());
        User seller = book.getOwner();

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
        offersPending.forEach(offer -> bookService.freeBook(offer.getBookToPay()));
        bookService.freeBook(current.getBookToSell());
        current.getOffers().forEach(offer -> userService.removeOfferFromUser(offer.getCustomer(), offer));
        userService.removeTicketFromUser(current.getSeller(),current);
        ticketRepository.delete(current);
    }


    @Override
    public Ticket removeOfferFromTicket(Ticket ticket, Offer offer) {
        ticket.getOffers().remove(offer);
        return ticketRepository.save(ticket);
    }
}

