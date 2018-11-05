package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class TicketToBeCreatedDTO {

    private BookToBeDisplayedDTO bookToSell;

    private String description;
}
