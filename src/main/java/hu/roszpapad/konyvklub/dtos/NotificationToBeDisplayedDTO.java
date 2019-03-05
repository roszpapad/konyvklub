package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationToBeDisplayedDTO {

    private String ticketId;

    private String channelId;

    private String message;

    private String givenBookName;

    private String offeredBookName;

    private LocalDateTime createDate;
}
