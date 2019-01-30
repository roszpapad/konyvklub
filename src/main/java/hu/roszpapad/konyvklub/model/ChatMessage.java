package hu.roszpapad.konyvklub.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;

    private String recipientName;

    @ManyToOne
    @JoinColumn(name = "chat_channel_id")
    private ChatChannel chatChannel;

    private String message;

    private LocalDateTime createdDate;
}
