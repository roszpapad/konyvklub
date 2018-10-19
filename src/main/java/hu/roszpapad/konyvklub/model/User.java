package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean admin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Book> books = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Ticket> ticketsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Offer> offersInInterest = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setUser(this);
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Ticket> getTicketsCreated() {
        return ticketsCreated;
    }

    public void setTicketsCreated(List<Ticket> ticketsCreated) {
        this.ticketsCreated = ticketsCreated;
    }

    public List<Offer> getOffersInInterest() {
        return offersInInterest;
    }

    public void setOffersInInterest(List<Offer> offersInInterest) {
        this.offersInInterest = offersInInterest;
    }

    public User addTicket(Ticket ticket){
        if (!ticketsCreated.contains(ticket))
            this.ticketsCreated.add(ticket);

        return this;
    }

    public User addBook(Book book){
        book.setOwner(this);
        if (!books.contains(book))
            this.books.add(book);

        return this;
    }

    public User addOffer(Offer offer){
        if (!offersInInterest.contains(offer))
            this.offersInInterest.add(offer);

        return this;
    }
}
