package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.TicketToBeDisplayed;
import hu.roszpapad.konyvklub.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketToBeDisplayedConverter implements Converter<Ticket,TicketToBeDisplayed>{

    private final ModelMapper modelMapper;

    @Override
    public TicketToBeDisplayed toDTO(Ticket entity) {
        return modelMapper.map(entity,TicketToBeDisplayed.class);
    }

    @Override
    public Ticket toEntity(TicketToBeDisplayed dto) {
        return modelMapper.map(dto, Ticket.class);
    }
}
