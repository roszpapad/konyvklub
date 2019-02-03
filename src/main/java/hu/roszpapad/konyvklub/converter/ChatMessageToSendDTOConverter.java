package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.ChatMessageToSendDTO;
import hu.roszpapad.konyvklub.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageToSendDTOConverter implements Converter<ChatMessage, ChatMessageToSendDTO>{

    private final ModelMapper modelMapper;

    @Override
    public ChatMessageToSendDTO toDTO(ChatMessage entity) {
        return modelMapper.map(entity, ChatMessageToSendDTO.class);
    }

    @Override
    public ChatMessage toEntity(ChatMessageToSendDTO dto) {
        return modelMapper.map(dto, ChatMessage.class);
    }
}
