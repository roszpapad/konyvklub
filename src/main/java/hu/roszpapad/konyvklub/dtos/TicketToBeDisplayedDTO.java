package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.model.Offer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketToBeDisplayedDTO {

    private Long id;
    private BookToBeDisplayedDTO bookToSell;
    private List<Offer> offers;
    private Boolean open;
    private String description;
    private UserToBeDisplayedDTO seller;
    private LocalDateTime endDate;
}
