package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.ChatChannelToDisplayDTO;
import hu.roszpapad.konyvklub.model.ChatChannel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatChannelToDisplayDTOConverter {

    private final ModelMapper modelMapper;

    public ChatChannelToDisplayDTO toDTO(ChatChannel entity) {
        return modelMapper.map(entity, ChatChannelToDisplayDTO.class);
    }

    public ChatChannel toEntity(ChatChannelToDisplayDTO dto) {
        return modelMapper.map(dto,ChatChannel.class);
    }
}
