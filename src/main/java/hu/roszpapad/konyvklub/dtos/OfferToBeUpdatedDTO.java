package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class OfferToBeUpdatedDTO {

    private Long id;
    private Long bookId;
    private String description;
}
