package hu.roszpapad.konyvklub.exceptions;

public class OfferNotFoundException extends NotFoundException {

    public OfferNotFoundException() {
        super();
    }

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
