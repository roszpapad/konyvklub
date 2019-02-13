package hu.roszpapad.konyvklub.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reporter;

    private String reported;

    @Lob
    private String image;
}
