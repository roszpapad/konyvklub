package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest,Long>{

    List<FriendRequest> findAll();
    List<FriendRequest> findByRequestStarter(String requestStarter);
    List<FriendRequest> findByRequestDestination(String requestDestination);
    Optional<FriendRequest> findByRequestStarterAndRequestDestination(String requestStarter, String requestDestination);

}
