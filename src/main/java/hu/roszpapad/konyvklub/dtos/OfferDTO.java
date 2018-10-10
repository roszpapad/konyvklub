package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Status;
import lombok.Data;

@Data
public class OfferDTO {

    private Long id;
    private Book bookToPay;
    private Status status = Status.PENDING;
    private UserDTO customer;

}
