package hu.roszpapad.konyvklub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TicketClosedException extends RuntimeException {

    public TicketClosedException() {
        super();
    }

    public TicketClosedException(String message) {
        super(message);
    }

    public TicketClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
