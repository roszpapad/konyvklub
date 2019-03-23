package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.model.ChatChannel;
import hu.roszpapad.konyvklub.repositories.ChatChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatChannelServiceImpl implements ChatChannelService {

    private final ChatChannelRepository chatChannelRepository;

    @Override
    public ChatChannel findById(Long channelId) {
        return chatChannelRepository.findById(channelId).orElseThrow(() -> new NotFoundException(ChatChannel.class));
    }

    @Override
    public List<ChatChannel> findBusinessByUsername(String username) {
        return chatChannelRepository.findUserBusinessChannels(username);
    }

    @Override
    public ChatChannel createBusinessChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay) {
        ChatChannel channel = new ChatChannel(usernameOne, usernameTwo, bookToSell, bookToPay);
        return chatChannelRepository.save(channel);
    }

    @Override
    public List<ChatChannel> findFriendlyByUsername(String username) {
        return chatChannelRepository.findUserFriendlyChannels(username);
    }

    @Override
    public ChatChannel createFriendlyChatChannel(String usernameOne, String usernameTwo) {
        ChatChannel channel = new ChatChannel(usernameOne, usernameTwo);
        return chatChannelRepository.save(channel);
    }

    @Override
    public boolean friendlyChannelExists(String usernameOne, String usernameTwo) {
        Optional<ChatChannel> channel = chatChannelRepository.findFriendlyChannel(usernameOne, usernameTwo);
        return channel.isPresent();
    }
}
