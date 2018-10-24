
package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayed;
import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayed;
import hu.roszpapad.konyvklub.exceptions.OfferNotFoundException;
import hu.roszpapad.konyvklub.exceptions.TicketNotFoundException;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final UserService userService;

    private final BookService bookService;

    private final TicketRepository ticketRepository;

    private final Converter<Ticket,TicketToBeDisplayed> diplayableTicketConverter;

    private final Converter<Offer,OfferToBeDisplayed> displayableOfferConverter;

    private final OfferRepository offerRepository;


    @Override
    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public TicketToBeDisplayed getTicketDTOById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket with id :" + id + " not found"));
        return diplayableTicketConverter.toDTO(ticket);
    }

    @Override
    public Iterable<TicketToBeDisplayed> getTicketDTOs() {
        List<TicketToBeDisplayed> ticketDTOs = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket -> ticketDTOs.add(diplayableTicketConverter.toDTO(ticket)));
        return ticketDTOs;
    }

    @Override
    public Ticket acceptOffer(Long ticketId, Long offerId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException());
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new OfferNotFoundException());
        offer.setStatus(Status.ACCEPTED);
        ticket.setOpen(false);

        Book soldBook = ticket.getBookToSell();
        Book paidBook = offer.getBookToPay();

        User seller = ticket.getSeller();
        User customer = offer.getCustomer();

        seller.addBook(paidBook);
        seller.getBooks().remove(soldBook);
        customer.addBook(soldBook);
        customer.getBooks().remove(paidBook);

        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket current = ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new TicketNotFoundException());

        current.setDescription(ticket.getDescription());

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket current = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException());

        List<Offer> offersPending = current.getOffers().stream().filter(offer -> offer.getStatus().equals(Status.PENDING))
                .collect(Collectors.toList());
        offersPending.forEach(offer -> bookService.freeBook(offer.getBookToPay()));
        current.getOffers().forEach(offer -> userService.removeOfferFromUser(offer.getCustomer(), offer));
        userService.removeTicketFromUser(current.getSeller(),current);
        ticketRepository.delete(current);
    }
}

