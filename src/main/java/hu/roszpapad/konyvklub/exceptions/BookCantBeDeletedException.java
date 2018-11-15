package hu.roszpapad.konyvklub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookCantBeDeletedException extends RuntimeException {

    public BookCantBeDeletedException() {
    }

    public BookCantBeDeletedException(String message) {
        super(message);
    }

    public BookCantBeDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
