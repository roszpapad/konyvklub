package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.PERSIST)
    private Book bookToSell;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<Offer> offers = new ArrayList<>();

    private Boolean open = true;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
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
        bookToSell.setTicket(this);
        this.bookToSell = bookToSell;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
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
        seller.addTicket(this);
        this.seller = seller;
    }

    public Ticket addOffer(Offer offer){
        offer.setTicket(this);
        if (!offers.contains(offer))
            this.offers.add(offer);

        return this;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
