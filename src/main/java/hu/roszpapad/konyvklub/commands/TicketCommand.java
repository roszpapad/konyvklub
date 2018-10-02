package hu.roszpapad.konyvklub.commands;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TicketCommand {


    private Long id;
    private BookCommand bookToSell;
    private Set<OfferCommand> offers = new HashSet<>();
    private String description;
    //private UserCommand seller; //todo private Long sellerId;
    private LocalDateTime endDate;

    public TicketCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCommand getBookToSell() {
        return bookToSell;
    }

    public void setBookToSell(BookCommand bookToSell) {
        this.bookToSell = bookToSell;
    }

    public Set<OfferCommand> getOffers() {
        return offers;
    }

    public void setOffers(Set<OfferCommand> offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public UserCommand getSeller() {
        return seller;
    }

    public void setSeller(UserCommand seller) {
        this.seller = seller;
    }*/

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
