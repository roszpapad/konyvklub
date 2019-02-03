package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.ChatMessageToGetDTO;
import hu.roszpapad.konyvklub.dtos.ChatMessageToSendDTO;
import hu.roszpapad.konyvklub.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> getMessagesByChannel(Long channelId);

    ChatMessage saveMessage(Long channelId, ChatMessageToGetDTO messageGot);

    ChatMessageToSendDTO prepareForSending(ChatMessage message);
}
