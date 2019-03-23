package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TicketToBeCreatedDTO {

    @NotNull
    private Long bookId;

    @NotNull
    private String description;
}
