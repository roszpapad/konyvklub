package hu.roszpapad.konyvklub.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ChatChannel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usernameOne;

    private String usernameTwo;

    private String bookToSell;

    private String bookToPay;

    public ChatChannel(String usernameOne, String usernameTwo) {
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
        this.bookToSell = null;
        this.bookToPay = null;
    }

    public ChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay) {
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
        this.bookToSell = bookToSell;
        this.bookToPay = bookToPay;
    }

    public ChatChannel() {
    }
}
