package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.ChatChannel;
import hu.roszpapad.konyvklub.repositories.ChatChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatChannelServiceImpl implements ChatChannelService {

    private final ChatChannelRepository chatChannelRepository;

    @Override
    public ChatChannel findById(Long channelId) {
        //TODO exception handling
        return chatChannelRepository.findById(channelId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<ChatChannel> findByUsername(String username) {
        return chatChannelRepository.findUserChannels(username);
    }

    @Override
    public ChatChannel createChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay) {
        ChatChannel channel = new ChatChannel(usernameOne, usernameTwo, bookToSell, bookToPay);
        return chatChannelRepository.save(channel);
    }
}
