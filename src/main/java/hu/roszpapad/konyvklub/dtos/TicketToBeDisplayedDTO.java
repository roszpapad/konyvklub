package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketToBeDisplayedDTO {

    private Long id;
    private BookToBeDisplayedDTO bookToSell;
    private List<OfferToBeDisplayedDTO> offers;
    private String description;
    private UserToBeDisplayedDTO seller;
    private LocalDateTime endDate;
}
