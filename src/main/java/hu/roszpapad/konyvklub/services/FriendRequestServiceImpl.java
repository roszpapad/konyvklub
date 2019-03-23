package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.FriendRequestToBeCreatedDTO;
import hu.roszpapad.konyvklub.exceptions.FriendRequestCantBeCreatedException;
import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.model.FriendRequest;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.FriendRequestRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final ChatChannelService chatChannelService;

    @Override
    public FriendRequest createRequest(FriendRequestToBeCreatedDTO requestDTO) {
        String usernameOne = requestDTO.getRequestStarter();
        String usernameTwo = requestDTO.getRequestDestination();
        if (chatChannelService.friendlyChannelExists(usernameOne, usernameTwo) || wasRequestedYet(usernameOne,usernameTwo)){
            throw new FriendRequestCantBeCreatedException("Barátkérelmet már elküldve ezelőtt / már barátok.");
        }
        FriendRequest request = new FriendRequest();
        request.setRequestDestination(requestDTO.getRequestDestination());
        request.setRequestStarter(requestDTO.getRequestStarter());
        request.setAccepted(false);
        return friendRequestRepository.save(request);
    }

    @Override
    public void acceptRequest(Long id) {
        FriendRequest request = friendRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(FriendRequest.class));
        request.setAccepted(true);
        friendRequestRepository.save(request);
        User requestDestination = userRepository.findByUsername(request.getRequestDestination()).orElseThrow(() -> new NotFoundException(User.class));
        User requestStarter = userRepository.findByUsername(request.getRequestStarter()).orElseThrow(() -> new NotFoundException(User.class));
        if (!chatChannelService.friendlyChannelExists(requestStarter.getUsername(), requestDestination.getUsername())){
            chatChannelService.createFriendlyChatChannel(requestStarter.getUsername(), requestDestination.getUsername());
        }
    }

    @Override
    public void rejectRequest(Long id) {
        FriendRequest request = friendRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(FriendRequest.class));
        friendRequestRepository.delete(request);
    }

    @Override
    public List<FriendRequest> getRequestsByStarter(String starter) {
        List<FriendRequest> requests = friendRequestRepository.findByRequestStarter(starter);
        return requests.stream()
                .filter(request -> !request.isAccepted())
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendRequest> getRequestsByDestination(String destination) {
        List<FriendRequest> requests = friendRequestRepository.findByRequestDestination(destination);
        return requests.stream()
                .filter(request -> !request.isAccepted())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean wasRequestedYet(String starter, String destination) {
        Optional<FriendRequest> friendRequestOptional1 = friendRequestRepository.findByRequestStarterAndRequestDestination(starter,destination);
        Optional<FriendRequest> friendRequestOptional2 = friendRequestRepository.findByRequestStarterAndRequestDestination(destination,starter);
        return friendRequestOptional1.isPresent() || friendRequestOptional2.isPresent();
    }
}
