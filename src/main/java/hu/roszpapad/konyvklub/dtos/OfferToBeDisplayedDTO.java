package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Status;
import lombok.Data;

@Data
public class OfferToBeDisplayedDTO {

    private Long id;
    private BookToBeDisplayedDTO bookToPay;
    private Status status;
    private UserToBeDisplayedDTO customer;
    private Long ticketId;
}
