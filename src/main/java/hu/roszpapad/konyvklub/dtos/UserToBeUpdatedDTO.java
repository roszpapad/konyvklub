package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserToBeUpdatedDTO {

    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    private String lastName;

    @Valid
    private AddressToBeSavedDTO address;
}
