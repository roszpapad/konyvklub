package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class BookToBeCreatedDTO {

    private String title;
    private String writer;
    private String publisher;
    private Integer yearOfPublishing;
    private String isbn;
    private Boolean offerable = true;
}
