package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.ChatMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long>{

    List<ChatMessage> findByChatChannelId(String id);
}
