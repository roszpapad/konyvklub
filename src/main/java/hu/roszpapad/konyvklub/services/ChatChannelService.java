package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.ChatChannel;

import java.util.List;

public interface ChatChannelService {

    ChatChannel findById(Long channelId);
    List<ChatChannel> findByUsername(String username);
    ChatChannel createChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay);
}