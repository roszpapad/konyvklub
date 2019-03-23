package hu.roszpapad.konyvklub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FriendRequestCantBeCreatedException extends RuntimeException {
    public FriendRequestCantBeCreatedException() {
    }

    public FriendRequestCantBeCreatedException(String message) {
        super(message);
    }

    public FriendRequestCantBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
