package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class ChatChannelToDisplayDTO {

    private Long id;

    private String usernameOne;

    private String usernameTwo;

    private String bookToSell;

    private String bookToPay;
}
