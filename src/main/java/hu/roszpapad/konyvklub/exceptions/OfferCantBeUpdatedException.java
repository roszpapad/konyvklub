package hu.roszpapad.konyvklub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OfferCantBeUpdatedException extends RuntimeException {

    public OfferCantBeUpdatedException() {
    }

    public OfferCantBeUpdatedException(String message) {
        super(message);
    }

    public OfferCantBeUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
