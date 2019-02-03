package hu.roszpapad.konyvklub.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Lob
    private String message;

    private Long ticketId;

    private Long channelId;

    private String givenBookName;

    private String offeredBookName;

    private LocalDateTime endDate;
}
