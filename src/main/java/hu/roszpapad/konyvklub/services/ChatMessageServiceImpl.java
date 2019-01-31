package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.ChatMessageToGetDTO;
import hu.roszpapad.konyvklub.dtos.ChatMessageToSendDTO;
import hu.roszpapad.konyvklub.model.ChatChannel;
import hu.roszpapad.konyvklub.model.ChatMessage;
import hu.roszpapad.konyvklub.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatChannelService chatChannelService;

    @Override
    public List<ChatMessage> getMessagesByChannel(String channelId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatChannelIdOrderByCreatedDateDesc(channelId);
        return messages;
    }

    @Override
    public ChatMessage saveMessage(String channelId, ChatMessageToGetDTO messageGot) {
        ChatMessage messageToSave = new ChatMessage();
        ChatChannel chatChannel = chatChannelService.findById(channelId);
        messageToSave.setChatChannel(chatChannel);
        messageToSave.setCreatedDate(LocalDateTime.now());
        messageToSave.setMessage(messageGot.getMessage());
        messageToSave.setRecipientName(messageGot.getRecipientName());
        messageToSave.setSenderName(messageGot.getSenderName());
        return chatMessageRepository.save(messageToSave);
    }

    @Override
    public ChatMessageToSendDTO prepareForSending(ChatMessage message) {
        ChatMessageToSendDTO messageDTO = new ChatMessageToSendDTO();

        messageDTO.setCreatedDate(message.getCreatedDate());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setSenderName(message.getSenderName());
        return messageDTO;
    }
}
