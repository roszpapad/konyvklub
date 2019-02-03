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

    public ChatChannel(String usernameOne, String usernameTwo) {
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
    }

    public ChatChannel() {
    }
}
