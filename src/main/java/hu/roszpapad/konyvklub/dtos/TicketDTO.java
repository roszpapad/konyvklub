package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Book;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class TicketDTO {

    private Long id;
    private Book bookToSell;
    private Set<OfferDTO> offers = new HashSet<>();
    private String description;
    private UserDTO seller;
    private LocalDateTime endDate;

}
