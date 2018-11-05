package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class BookToBeDisplayedDTO {

    private Long id;
    private String title;
    private String writer;
    private String publisher;
    private Integer yearOfPublishing;
    private Boolean offerable;
}
