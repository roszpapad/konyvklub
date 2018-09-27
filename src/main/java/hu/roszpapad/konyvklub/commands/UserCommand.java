package hu.roszpapad.konyvklub.commands;

import hu.roszpapad.konyvklub.model.Address;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;

import java.util.HashSet;
import java.util.Set;

public class UserCommand {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean admin;
    private Set<Book> books = new HashSet<>();
    private Address address;
    private Set<Ticket> ticketsCreated = new HashSet<>();
    private Set<Offer> offersInInterest = new HashSet<>();

    public UserCommand() {
    }

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Ticket> getTicketsCreated() {
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
    }
}
