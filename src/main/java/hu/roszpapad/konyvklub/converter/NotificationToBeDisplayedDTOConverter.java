package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.NotificationToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Notification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationToBeDisplayedDTOConverter implements Converter<Notification, NotificationToBeDisplayedDTO> {

    private final ModelMapper modelMapper;

    @Override
    public NotificationToBeDisplayedDTO toDTO(Notification entity) {
        return null;
    }

    @Override
    public Notification toEntity(NotificationToBeDisplayedDTO dto) {
        return null;
    }
}
