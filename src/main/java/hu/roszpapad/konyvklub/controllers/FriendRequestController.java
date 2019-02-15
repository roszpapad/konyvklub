package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.FriendRequestToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.FriendRequestToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.FriendRequestToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.FriendRequest;
import hu.roszpapad.konyvklub.services.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    private final FriendRequestToBeDisplayedDTOConverter friendRequestToBeDisplayedDTOConverter;

    @PostMapping("/friendRequests/create")
    public ResponseEntity<FriendRequestToBeDisplayedDTO> createRequest(@Valid @RequestBody FriendRequestToBeCreatedDTO requestDTO){
        FriendRequest request = friendRequestService.createRequest(requestDTO);
        return ResponseEntity.ok(friendRequestToBeDisplayedDTOConverter.toDTO(request));
    }

    @GetMapping("/friendRequests/{requestId}/accept")
    public ResponseEntity<String> acceptRequest(@PathVariable(name = "requestId") Long id){
        friendRequestService.acceptRequest(id);
        return ResponseEntity.ok("Barátkérelem elfogadva.");
    }

    @GetMapping("/friendRequests/{requestId}/reject")
    public ResponseEntity<String> rejectRequest(@PathVariable(name = "requestId") Long id){
        friendRequestService.rejectRequest(id);
        return ResponseEntity.ok("Barátkérelem elutasitva.");
    }

    @GetMapping("/friendRequests/getByStarter")
    public ResponseEntity<List<FriendRequestToBeDisplayedDTO>> getRequestsByStarter(@PathParam(value = "requestStarter") String requestStarter){
        List<FriendRequest> requests = friendRequestService.getRequestsByStarter(requestStarter);
        List<FriendRequestToBeDisplayedDTO> requestDTOs = new ArrayList<>();
        requests.forEach(request -> requestDTOs.add(friendRequestToBeDisplayedDTOConverter.toDTO(request)));
        return ResponseEntity.ok(requestDTOs);
    }

    @GetMapping("/friendRequests/getByDestination")
    public ResponseEntity<List<FriendRequestToBeDisplayedDTO>> getRequestsByDestination(@PathParam(value = "requestDestination") String requestDestination){
        List<FriendRequest> requests = friendRequestService.getRequestsByDestination(requestDestination);
        List<FriendRequestToBeDisplayedDTO> requestDTOs = new ArrayList<>();
        requests.forEach(request -> requestDTOs.add(friendRequestToBeDisplayedDTOConverter.toDTO(request)));
        return ResponseEntity.ok(requestDTOs);
    }

    @GetMapping("/friendRequests/wasRequestedYet")
    public ResponseEntity<String> wasRequestedYet(@PathParam(value = "requestStarter") String requestStarter,
                                                  @PathParam(value = "requestDestination") String requestDestination){
        return ResponseEntity.ok(friendRequestService.wasRequestedYet(requestStarter,requestDestination).toString());
    }
}
