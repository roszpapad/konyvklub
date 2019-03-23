package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OfferToBeSavedDTO {

    @NotNull
    private Long bookId;

    @NotNull
    private Long ticketId;

    @NotNull
    private String description;
}
