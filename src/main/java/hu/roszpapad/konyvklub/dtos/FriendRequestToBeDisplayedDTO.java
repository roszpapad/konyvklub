package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class FriendRequestToBeDisplayedDTO {

    private Long id;

    private String requestStarter;

    private String requestDestination;
}
