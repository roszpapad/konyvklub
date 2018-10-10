package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.OfferDTO;
import hu.roszpapad.konyvklub.dtos.TicketDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;



    @Override
    public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public TicketDTO getTicketDTOById(Long id) {
        return modelMapper.map(ticketRepository.findById(id).get(),TicketDTO.class);
    }

    @Override
    public Iterable<TicketDTO> getTicketDTOs() {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket -> ticketDTOs.add(modelMapper.map(ticket,TicketDTO.class)));
        return ticketDTOs;
    }

    @Override
    public List<OfferDTO> getOfferDTOs(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            List<OfferDTO> offerDTOs = new ArrayList<>();
            ticket.getOffers().forEach(offer -> offerDTOs.add(modelMapper.map(offer,OfferDTO.class)));
            return offerDTOs;
        }
        else {
            return null; //todo error
        }
    }

    @Override
    public OfferDTO getOfferDTO(Long ticketId, Long offerId) {

        OfferDTO offerDTO = getOfferDTOs(ticketId).stream()
                .filter(offer -> offer.getId().equals(offerId))
                .findFirst().get();

        return offerDTO;
    }
}
