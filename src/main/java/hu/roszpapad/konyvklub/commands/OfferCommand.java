package hu.roszpapad.konyvklub.commands;

import hu.roszpapad.konyvklub.model.Status;

public class OfferCommand {

    private Long id;
    private BookCommand bookToPay;
    private TicketCommand ticket;
    private Status status;
    private UserCommand customer;

    public OfferCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCommand getBookToPay() {
        return bookToPay;
    }

    public void setBookToPay(BookCommand bookToPay) {
        this.bookToPay = bookToPay;
    }

    public TicketCommand getTicket() {
        return ticket;
    }

    public void setTicket(TicketCommand ticket) {
        this.ticket = ticket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserCommand getCustomer() {
        return customer;
    }

    public void setCustomer(UserCommand customer) {
        this.customer = customer;
    }
}
