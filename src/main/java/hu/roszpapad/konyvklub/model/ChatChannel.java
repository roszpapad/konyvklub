package hu.roszpapad.konyvklub.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "chat_channel")
public class ChatChannel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usernameOne;

    private String usernameTwo;

    private String bookToSell;

    private String bookToPay;

    public ChatChannel(String usernameOne, String usernameTwo, String bookToSell, String bookToPay) {
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
        this.bookToSell = bookToSell;
        this.bookToPay = bookToPay;
    }

    public ChatChannel() {
    }
}
