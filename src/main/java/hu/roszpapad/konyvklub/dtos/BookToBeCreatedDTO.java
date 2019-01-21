package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookToBeCreatedDTO {

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    private String writer;

    @NotNull
    @Size(min = 1, max = 100)
    private String publisher;

    @NotNull
    private Integer yearOfPublishing;
}
