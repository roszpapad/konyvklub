package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Status;
import lombok.Data;

@Data
public class OfferToBeSavedDTO {

    private Long bookId;
    private Status status = Status.PENDING;
    private Long ticketId;
    private String description;
}
