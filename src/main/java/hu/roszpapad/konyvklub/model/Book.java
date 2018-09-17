package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private String publisher;
    private Byte[] image;
    private LocalDateTime dateOfPublishing;

    @OneToOne(mappedBy = "bookToSell")
    private Ticket sellingTicket;

    @OneToOne(mappedBy = "bookToPay")
    private Ticket offeringTicket;

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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public LocalDateTime getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(LocalDateTime dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Ticket getSellingTicket() {
        return sellingTicket;
    }

    public void setSellingTicket(Ticket sellingTicket) {
        this.sellingTicket = sellingTicket;
    }

    public Ticket getOfferingTicket() {
        return offeringTicket;
    }

    public void setOfferingTicket(Ticket offeringTicket) {
        this.offeringTicket = offeringTicket;
    }
}
