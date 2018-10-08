package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Set<Book> books = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

   /* @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Ticket> ticketsCreated = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Offer> offersInInterest = new HashSet<>();*/

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
        //address.setUser(this);
        this.address = address;
    }

   /* public Set<Ticket> getTicketsCreated() {
        return ticketsCreated;
    }

    public void setTicketsCreated(Set<Ticket> ticketsCreated) {
        this.ticketsCreated = ticketsCreated;
    }

    public Set<Offer> getOffersInInterest() {
        return offersInInterest;
    }

    public void setOffersInInterest(Set<Offer> offersInInterest) {
        this.offersInInterest = offersInInterest;
    }*/

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /*public User addTicket(Ticket ticket){
        ticket.setSeller(this);
        this.getTicketsCreated().add(ticket);
        return this;
    }*/

    public User addBook(Book book){
        this.getBooks().add(book);
        return this;
    }

    /*public User addOffer(Offer offer){
        offer.setCustomer(this);
        this.getOffersInInterest().add(offer);
        return this;
    }*/
}
