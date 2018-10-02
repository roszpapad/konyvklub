package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book bookToSell;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set<Offer> offers = new HashSet<>();

    @Lob
    private String description;

    @ManyToOne
    private User seller;

    private LocalDateTime endDate;

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
        bookToSell.setSellingTicket(this);
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
        Set<Ticket> tickets = seller.getTicketsCreated();
        tickets.add(this);
        seller.setTicketsCreated(tickets);
        this.seller = seller;
    }
}
