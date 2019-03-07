package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.ChatChannelToDisplayDTO;
import hu.roszpapad.konyvklub.dtos.ChatMessageToGetDTO;
import hu.roszpapad.konyvklub.dtos.ChatMessageToSendDTO;
import hu.roszpapad.konyvklub.model.ChatChannel;
import hu.roszpapad.konyvklub.model.ChatMessage;
import hu.roszpapad.konyvklub.services.ChatChannelService;
import hu.roszpapad.konyvklub.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatChannelService chatChannelService;

    private final ChatMessageService chatMessageService;

    private final Converter<ChatChannel, ChatChannelToDisplayDTO> chatChannelToDisplayDTOConverter;

    private final Converter<ChatMessage, ChatMessageToSendDTO> chatMessageToSendDTOConverter;

    @MessageMapping("/chat/{channelId}")
    @SendTo("/topic/chat/{channelId}")
    public ChatMessageToSendDTO sendMessage(@DestinationVariable Long channelId,
                                                            ChatMessageToGetDTO messageGot){

        ChatMessage message = chatMessageService.saveMessage(channelId, messageGot);
        return chatMessageService.prepareForSending(message);
    }

    @GetMapping("/users/{username}/friendlyChannels")
    public ResponseEntity<List<ChatChannelToDisplayDTO>> getFriendlyChannels(@PathVariable(name = "username") String username){

        List<ChatChannel> channels = chatChannelService.findFriendlyByUsername(username);

        List<ChatChannelToDisplayDTO> channelsDTO = new ArrayList<>();

        channels.forEach(chatChannel -> channelsDTO.add(chatChannelToDisplayDTOConverter.toDTO(chatChannel)));

        return ResponseEntity.ok(channelsDTO);
    }

    @GetMapping("/users/{username}/businessChannels")
    public ResponseEntity<List<ChatChannelToDisplayDTO>> getBusinessChannels(@PathVariable(name = "username") String username){

        List<ChatChannel> channels = chatChannelService.findBusinessByUsername(username);

        List<ChatChannelToDisplayDTO> channelsDTO = new ArrayList<>();

        channels.forEach(chatChannel -> channelsDTO.add(chatChannelToDisplayDTOConverter.toDTO(chatChannel)));

        return ResponseEntity.ok(channelsDTO);
    }

    @GetMapping("/users/channels/{channelId}")
    public ResponseEntity<ChatChannelToDisplayDTO> getChannel(@PathVariable(name = "channelId") Long channelId){
        return ResponseEntity.ok(chatChannelToDisplayDTOConverter.toDTO(chatChannelService.findById(channelId)));
    }

    @GetMapping("/channels/{channelId}/messages")
    public ResponseEntity<List<ChatMessageToSendDTO>> getMessagesByChannel(@PathVariable(name = "channelId") Long channelId){

        List<ChatMessage> messages = chatMessageService.getMessagesByChannel(channelId);
        List<ChatMessageToSendDTO> messageDTOs = new ArrayList<>();
        messages.forEach(message -> messageDTOs.add(chatMessageToSendDTOConverter.toDTO(message)));
        return ResponseEntity.ok(messageDTOs);

    }
}
