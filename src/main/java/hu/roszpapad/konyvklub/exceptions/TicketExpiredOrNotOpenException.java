package hu.roszpapad.konyvklub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class TicketExpiredOrNotOpenException extends RuntimeException {

    public TicketExpiredOrNotOpenException() {
        super();
    }

    public TicketExpiredOrNotOpenException(String message) {
        super(message);
    }

    public TicketExpiredOrNotOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
