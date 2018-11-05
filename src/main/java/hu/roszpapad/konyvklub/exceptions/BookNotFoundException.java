package hu.roszpapad.konyvklub.exceptions;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
