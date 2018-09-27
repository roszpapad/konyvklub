package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book bookToPay;

    @ManyToOne
    private Ticket ticket;


    @Enumerated(value = EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    private User customer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBookToPay() {
        return bookToPay;
    }

    public void setBookToPay(Book bookToPay) {
        bookToPay.setOffer(this);
        this.bookToPay = bookToPay;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        Set<Offer> ticketSet = customer.getOffersInInterest();
        ticketSet.add(this);
        customer.setOffersInInterest(ticketSet);
        this.customer = customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
