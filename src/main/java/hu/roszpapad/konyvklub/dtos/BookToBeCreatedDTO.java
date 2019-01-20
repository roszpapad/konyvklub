package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookToBeCreatedDTO {

    @NotNull
    private String title;

    @NotNull
    private String writer;

    @NotNull
    private String publisher;

    @NotNull
    private Integer yearOfPublishing;
    private String isbn;
    private Boolean offerable = true;
}
