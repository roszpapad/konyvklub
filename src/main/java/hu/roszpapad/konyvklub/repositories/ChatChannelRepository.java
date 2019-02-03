package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.ChatChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatChannelRepository extends CrudRepository<ChatChannel,Long>{


    @Query("Select c FROM ChatChannel c WHERE :username IN (c.usernameOne, c.usernameTwo)")
    List<ChatChannel> findUserChannels(@Param("username") String username);
}
