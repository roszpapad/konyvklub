package hu.roszpapad.konyvklub.commands;

import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TicketCommand {


    private Long id;
    private Book bookToSell;
    private Set<Offer> offers = new HashSet<>();
    private String description;
    private User seller;
    private LocalDateTime endDate;

    public TicketCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBookToSell() {
        return bookToSell;
    }

    public void setBookToSell(Book bookToSell) {
        this.bookToSell = bookToSell;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
