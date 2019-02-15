package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.validators.annotations.ValidFriendRequest;
import lombok.Data;

@Data
@ValidFriendRequest
public class FriendRequestToBeCreatedDTO {

    private String requestStarter;

    private String requestDestination;
}
