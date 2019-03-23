package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.ChatMessageToSendDTO;
import hu.roszpapad.konyvklub.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageToSendDTOConverter {

    private final ModelMapper modelMapper;

    public ChatMessageToSendDTO toDTO(ChatMessage entity) {
        return modelMapper.map(entity, ChatMessageToSendDTO.class);
    }

    public ChatMessage toEntity(ChatMessageToSendDTO dto) {
        return modelMapper.map(dto, ChatMessage.class);
    }
}
