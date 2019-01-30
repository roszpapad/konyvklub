package hu.roszpapad.konyvklub.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ChatChannel extends Auditable {

    @Id
    private String id;

    private String usernameOne;

    private String usernameTwo;

    public ChatChannel(String usernameOne, String usernameTwo) {
        this.id = UUID.randomUUID().toString();
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
    }

    public ChatChannel() {
    }
}
