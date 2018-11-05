package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Status;
import hu.roszpapad.konyvklub.model.User;
import lombok.Data;

@Data
public class OfferToBeSavedOrUpdatedDTO {

    private Long id;
    private Book bookToPay;
    private Status status = Status.PENDING;
    private User customer;
    private Long ticketId;
}
