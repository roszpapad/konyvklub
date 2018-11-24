
package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.*;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.TicketService;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final UserService userService;

    private final Converter<Ticket, TicketToBeCreatedDTO> ticketToBeCreatedDTOConverter;

    private final Converter<Ticket, TicketToBeDisplayedDTO> ticketToBeDisplayedDTOConverter;

    private final Converter<Ticket, TicketToBeUpdatedDTO> ticketToBeUpdatedDTOConverter;

    private final Converter<Book, BookToBeDisplayedDTO> bookToBeDisplayedDTOConverter;

    @GetMapping("/tickets/all")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getTickets();
        List<TicketToBeDisplayedDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> ticketDTOs.add(ticketToBeDisplayedDTOConverter.toDTO(ticket)));
        model.addAttribute("tickets",ticketDTOs);
        model.addAttribute("newTicket",new TicketToBeCreatedDTO());
        List<Book> books = userService.findById(1L).getBooks();
        List<BookToBeDisplayedDTO> bookDTOs = new ArrayList<>();
        books.forEach(book -> {
            if (book.getOfferable()){
                bookDTOs.add(bookToBeDisplayedDTOConverter.toDTO(book));
            }
        });
        model.addAttribute("userBooks",bookDTOs);
        return "tickets/tickets";
    }

    @GetMapping("/tickets/{ticketId}")
    public String getTicket(@PathVariable Long ticketId, Model model){
        model.addAttribute("ticket",ticketToBeDisplayedDTOConverter.toDTO(ticketService.findById(ticketId)));
        OfferToBeSavedDTO newOffer = new OfferToBeSavedDTO();
        newOffer.setTicketId(ticketId);
        model.addAttribute("newOffer", newOffer);
        List<Book> books = userService.findById(1L).getBooks();
        List<BookToBeDisplayedDTO> bookDTOs = new ArrayList<>();
        books.forEach(book -> {
            if (book.getOfferable()){
                bookDTOs.add(bookToBeDisplayedDTOConverter.toDTO(book));
            }
        });
        model.addAttribute("userBooks",bookDTOs);
        return "tickets/ticket";
    }

    @PostMapping("/tickets")
    public String createTicket(Model model, @ModelAttribute(name = "newTicket") TicketToBeCreatedDTO ticketDTO){
        Ticket ticket = ticketService.createTicket(ticketToBeCreatedDTOConverter.toEntity(ticketDTO));
        model.addAttribute("ticket", ticketToBeDisplayedDTOConverter.toDTO(ticket));
        model.addAttribute("newOffer", new OfferToBeSavedDTO());
        return "redirect:tickets/" + ticket.getId();
    }

    @GetMapping("/ticket/{ticketId}/update")
    public String updateTicket(Model model, @PathVariable("ticketId") Long ticketId){
        model.addAttribute("ticket",ticketToBeUpdatedDTOConverter.toDTO(ticketService.findById(ticketId)));
        return "tickets/update";
    }

    @PutMapping("/ticket/update")
    public String updateTicket(Model model, @ModelAttribute(name = "ticket") TicketToBeUpdatedDTO ticketDTO){
        Ticket ticket = ticketService.updateTicket(ticketToBeUpdatedDTOConverter.toEntity(ticketDTO));
        model.addAttribute("ticket",ticketToBeDisplayedDTOConverter.toDTO(ticket));
        return "redirect:tickets/ticket";
    }

    @DeleteMapping("/ticket/{ticketId}/delete")
    public String deleteTicket(@PathVariable("ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId);
        return "redirect:ticket/tickets";
    }

}

