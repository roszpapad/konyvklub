package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.OfferToBeSavedOrUpdated;
import hu.roszpapad.konyvklub.exceptions.TicketNotFoundException;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final TicketRepository ticketRepository;

    private final Converter<Offer, OfferToBeSavedOrUpdated> offerSaveOrUpdateConverter;

    @Override
    public Offer saveOfferDTO(OfferToBeSavedOrUpdated offerDTO) {

        Ticket ticket = ticketRepository.findById(offerDTO.getTicketId()).orElseThrow(() -> new TicketNotFoundException());
        Offer offer = offerSaveOrUpdateConverter.toEntity(offerDTO);

        ticket.addOffer(offer);

        ticketRepository.save(ticket);

        return offer;
    }
}
