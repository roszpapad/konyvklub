package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketToBeDisplayed {

    private Long id;
    private Book bookToSell;
    private List<Offer> offers;
    private Boolean open;
    private String description;
    private User seller;
    private LocalDateTime endDate;
}
