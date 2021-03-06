package hu.roszpapad.konyvklub.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String username;

    @Lob
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Book> books = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<Ticket> ticketsCreated = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Offer> offersInInterest = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    private Boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;

    private Boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
