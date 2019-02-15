package hu.roszpapad.konyvklub.exceptions;

public class FriendRequestNotFoundException extends NotFoundException {

    public FriendRequestNotFoundException() {
    }

    public FriendRequestNotFoundException(String message) {
        super(message);
    }

    public FriendRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
