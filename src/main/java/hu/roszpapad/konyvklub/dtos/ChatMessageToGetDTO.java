package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class ChatMessageToGetDTO {

    private String senderName;

    private String recipientName;

    private String message;
}
