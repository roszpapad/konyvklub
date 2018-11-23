package hu.roszpapad.konyvklub.model;

import javax.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_to_pay_id")
    private Book bookToPay;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Lob
    private String description;

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
        this.bookToPay = bookToPay;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        customer.addOffer(this);
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
