package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.FriendRequestToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {

    FriendRequest createRequest(FriendRequestToBeCreatedDTO requestDTO);
    void acceptRequest(Long id);
    void rejectRequest(Long id);
    List<FriendRequest> getRequestsByStarter(String starter);
    List<FriendRequest> getRequestsByDestination(String destination);
    Boolean wasRequestedYet(String starter, String destination);
}
