package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book bookToSell;

    @OneToOne
    private Book bookToPay;

    private String wantedBookDescription;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User customer;

    private LocalDateTime startTime;
    private Integer duration;

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

    public Book getBookToPay() {
        return bookToPay;
    }

    public void setBookToPay(Book bookToPay) {
        bookToPay.setOfferingTicket(this);
        this.bookToPay = bookToPay;
    }

    public String getWantedBookDescription() {
        return wantedBookDescription;
    }

    public void setWantedBookDescription(String wantedBookDescription) {
        this.wantedBookDescription = wantedBookDescription;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        Set<Ticket> ticketSet = seller.getTicketsCreated();
        ticketSet.add(this);
        seller.setTicketsCreated(ticketSet);
        this.seller = seller;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        Set<Ticket> ticketSet = customer.getTicketsInInterest();
        ticketSet.add(this);
        customer.setTicketsInInterest(ticketSet);
        this.customer = customer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
