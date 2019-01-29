package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class NotificationToBeDisplayedDTO {

    private String message;

    private String givenBookName;

    private String offeredBookName;
}
