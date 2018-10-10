package hu.roszpapad.konyvklub.model;

import javax.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book bookToPay;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne(optional = false)
    @JoinColumn(name = "costumer_id")
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
        //bookToPay.setOffer(this);
        this.bookToPay = bookToPay;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        /*Set<Offer> ticketSet = customer.getOffersInInterest();
        ticketSet.add(this);
        customer.setOffersInInterest(ticketSet);*/
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
