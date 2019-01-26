package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class TicketToBeCreatedDTO {

    private Long bookId;

    private String description;
}
