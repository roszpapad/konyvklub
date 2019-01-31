package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageToSendDTO {

    private String senderName;

    private LocalDateTime createdDate;

    private String message;
}
