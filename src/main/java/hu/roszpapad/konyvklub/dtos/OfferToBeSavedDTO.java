package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class OfferToBeSavedDTO {

    private Long bookId;
    private Long ticketId;
    private String description;
}
