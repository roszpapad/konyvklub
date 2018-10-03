package hu.roszpapad.konyvklub.commands;

import hu.roszpapad.konyvklub.model.Status;

public class OfferCommand {

    private Long id;
    private BookCommand bookToPay;
    private Long ticketId;
    private Status status;
    private Long customerId;

    public OfferCommand() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
