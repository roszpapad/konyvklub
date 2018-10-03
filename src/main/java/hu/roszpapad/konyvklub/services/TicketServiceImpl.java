package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.commands.OfferCommand;
import hu.roszpapad.konyvklub.commands.TicketCommand;
import hu.roszpapad.konyvklub.converters.OfferToOfferCommand;
import hu.roszpapad.konyvklub.converters.TicketToTicketCommand;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;
    private TicketToTicketCommand ticketToTicketCommand;
    private OfferToOfferCommand offerToOfferCommand;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketToTicketCommand ticketToTicketCommand, OfferToOfferCommand offerToOfferCommand) {
        this.ticketRepository = ticketRepository;
        this.ticketToTicketCommand = ticketToTicketCommand;
        this.offerToOfferCommand = offerToOfferCommand;
    }

    @Override
    public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public TicketCommand getCommandById(Long id) {
        return ticketToTicketCommand.convert(ticketRepository.findById(id).get());
    }

    @Override
    public Iterable<TicketCommand> getTicketCommands() {
        List<TicketCommand> ticketCommands = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket -> ticketCommands.add(ticketToTicketCommand.convert(ticket)));
        return ticketCommands;
    }

    @Override
    public Iterable<OfferCommand> getOfferCommands(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            List<OfferCommand> offerCommands = new ArrayList<>();
            ticket.getOffers().forEach(offer -> offerCommands.add(offerToOfferCommand.convert(offer)));
            return offerCommands;
        }
        else {
            return null; //todo error
        }
    }
}
