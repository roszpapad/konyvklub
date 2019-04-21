package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.FriendRequestToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.FriendRequestToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.FriendRequestToBeDisplayedDTO;
import hu.roszpapad.konyvklub.exceptions.FriendRequestCantBeCreatedException;
import hu.roszpapad.konyvklub.model.FriendRequest;
import hu.roszpapad.konyvklub.services.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    private final FriendRequestToBeDisplayedDTOConverter friendRequestToBeDisplayedDTOConverter;

    @PostMapping("/friendRequests/create")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<FriendRequestToBeDisplayedDTO> createRequest(@RequestBody FriendRequestToBeCreatedDTO requestDTO){
        FriendRequest request = friendRequestService.createRequest(requestDTO);
        return ResponseEntity.ok(friendRequestToBeDisplayedDTOConverter.toDTO(request));
    }

    @GetMapping("/friendRequests/{requestId}/accept")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<String> acceptRequest(@PathVariable(name = "requestId") Long id){
        friendRequestService.acceptRequest(id);
        return ResponseEntity.ok("Barátkérelem elfogadva.");
    }

    @GetMapping("/friendRequests/{requestId}/reject")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<String> rejectRequest(@PathVariable(name = "requestId") Long id){
        friendRequestService.rejectRequest(id);
        return ResponseEntity.ok("Barátkérelem elutasitva.");
    }

    @GetMapping("/friendRequests/getByStarter")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<List<FriendRequestToBeDisplayedDTO>> getRequestsByStarter(@PathParam(value = "requestStarter") String requestStarter){
        List<FriendRequest> requests = friendRequestService.getRequestsByStarter(requestStarter);
        List<FriendRequestToBeDisplayedDTO> requestDTOs = new ArrayList<>();
        requests.forEach(request -> requestDTOs.add(friendRequestToBeDisplayedDTOConverter.toDTO(request)));
        return ResponseEntity.ok(requestDTOs);
    }

    @GetMapping("/friendRequests/getByDestination")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<List<FriendRequestToBeDisplayedDTO>> getRequestsByDestination(@PathParam(value = "requestDestination") String requestDestination){
        List<FriendRequest> requests = friendRequestService.getRequestsByDestination(requestDestination);
        List<FriendRequestToBeDisplayedDTO> requestDTOs = new ArrayList<>();
        requests.forEach(request -> requestDTOs.add(friendRequestToBeDisplayedDTOConverter.toDTO(request)));
        return ResponseEntity.ok(requestDTOs);
    }

    @GetMapping("/friendRequests/wasRequestedYet")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<String> wasRequestedYet(@PathParam(value = "requestStarter") String requestStarter,
                                                  @PathParam(value = "requestDestination") String requestDestination){
        return ResponseEntity.ok(friendRequestService.wasRequestedYet(requestStarter,requestDestination).toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FriendRequestCantBeCreatedException.class)
    public ResponseEntity<?> handleCreateException(Exception exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
