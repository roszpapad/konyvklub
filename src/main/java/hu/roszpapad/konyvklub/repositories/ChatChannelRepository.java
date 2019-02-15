package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.ChatChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatChannelRepository extends CrudRepository<ChatChannel,Long>{


    @Query("Select c FROM ChatChannel c WHERE :username IN (c.usernameOne, c.usernameTwo) AND c.bookToSell IS NOT NULL")
    List<ChatChannel> findUserBusinessChannels(@Param("username") String username);

    @Query("Select c FROM ChatChannel c WHERE :username IN (c.usernameOne, c.usernameTwo) AND c.bookToSell IS NULL")
    List<ChatChannel> findUserFriendlyChannels(@Param("username") String username);

    @Query("Select c FROM ChatChannel c WHERE :username1 IN (c.usernameOne, c.usernameTwo) AND :username2 IN (c.usernameOne, c.usernameTwo) AND c.bookToSell IS NULL")
    Optional<ChatChannel> findFriendlyChannel(@Param("username1") String username1, @Param("username2") String username2);
}
