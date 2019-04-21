package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class FriendRequestToBeCreatedDTO {

    private String requestStarter;

    private String requestDestination;
}
