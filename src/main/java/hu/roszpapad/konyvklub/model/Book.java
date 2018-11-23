package hu.roszpapad.konyvklub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private String publisher;
    private Integer yearOfPublishing;
    private Boolean offerable = true;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
