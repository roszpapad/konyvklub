package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.ChatChannel;

import java.util.List;

public interface ChatChannelService {

    ChatChannel findById(Long channelId);
    List<ChatChannel> findBusinessByUsername(String username);
    List<ChatChannel> findFriendlyByUsername(String username);
    ChatChannel createBusinessChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay);
    ChatChannel createFriendlyChatChannel(String usernameOne, String usernameTwo);
    boolean friendlyChannelExists(String usernameOne, String usernameTwo);
}
