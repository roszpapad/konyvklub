package hu.roszpapad.konyvklub.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
public class ChangePasswordToken {

    private static final int EXPIRATION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    private LocalDateTime calculateExpiryDate(int days){
        return LocalDateTime.now().plus(days, ChronoUnit.DAYS);
    }

    public ChangePasswordToken() {
        super();
    }

    public ChangePasswordToken(String token, User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
