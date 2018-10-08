package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Status;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import lombok.Data;

@Data
public class OfferDTO {

    private Long id;
    private Book bookToPay;
    private Ticket ticket;
    private Status status = Status.PENDING;
    private User customer;

}
