package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.TicketToBeUpdatedDTO;
import hu.roszpapad.konyvklub.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketToBeUpdatedDTOConverter implements Converter<Ticket, TicketToBeUpdatedDTO>{

    private final ModelMapper modelMapper;

    @Override
    public TicketToBeUpdatedDTO toDTO(Ticket entity) {
        return modelMapper.map(entity, TicketToBeUpdatedDTO.class);
    }

    @Override
    public Ticket toEntity(TicketToBeUpdatedDTO dto) {
        return modelMapper.map(dto, Ticket.class);
    }
}
