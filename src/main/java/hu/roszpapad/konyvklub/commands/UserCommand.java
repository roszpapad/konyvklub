package hu.roszpapad.konyvklub.commands;

import java.util.HashSet;
import java.util.Set;

public class UserCommand {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean admin;
    private Set<BookCommand> books = new HashSet<>();
    private AddressCommand address;
    private Set<TicketCommand> ticketsCreated = new HashSet<>();
    private Set<OfferCommand> offersInInterest = new HashSet<>();

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

    public Set<BookCommand> getBooks() {
        return books;
    }

    public void setBooks(Set<BookCommand> books) {
        this.books = books;
    }

    public AddressCommand getAddress() {
        return address;
    }

    public void setAddress(AddressCommand address) {
        this.address = address;
    }

    public Set<TicketCommand> getTicketsCreated() {
        return ticketsCreated;
    }

    public void setTicketsCreated(Set<TicketCommand> ticketsCreated) {
        this.ticketsCreated = ticketsCreated;
    }

    public Set<OfferCommand> getOffersInInterest() {
        return offersInInterest;
    }

    public void setOffersInInterest(Set<OfferCommand> offersInInterest) {
        this.offersInInterest = offersInInterest;
    }
}
