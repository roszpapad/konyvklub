package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.NotificationToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Notification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationToBeDisplayedDTOConverter {

    private final ModelMapper modelMapper;

    public NotificationToBeDisplayedDTO toDTO(Notification entity) {
        return modelMapper.map(entity, NotificationToBeDisplayedDTO.class);
    }

    public Notification toEntity(NotificationToBeDisplayedDTO dto) {
        return modelMapper.map(dto, Notification.class);
    }
}
