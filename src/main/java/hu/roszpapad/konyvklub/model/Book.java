package hu.roszpapad.konyvklub.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private String publisher;

    private Integer yearOfPublishing;

    @ManyToOne
    private User owner;

    @OneToOne(mappedBy = "bookToSell")
    private Ticket sellingTicket;

    @OneToOne(mappedBy = "bookToPay")
    private Offer offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer dateOfPublishing) {
        this.yearOfPublishing = dateOfPublishing;
    }

    public Ticket getSellingTicket() {
        return sellingTicket;
    }

    public void setSellingTicket(Ticket sellingTicket) {
        this.sellingTicket = sellingTicket;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
