package hu.roszpapad.konyvklub.exceptions;

public class TicketNotFoundException extends NotFoundException {

    public TicketNotFoundException() {
        super();
    }

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
