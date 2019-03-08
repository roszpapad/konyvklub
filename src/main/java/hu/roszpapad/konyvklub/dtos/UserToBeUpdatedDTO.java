package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.Valid;

@Data
public class UserToBeUpdatedDTO {

    private Long id;
    private String firstName;
    private String lastName;

    @Valid
    private AddressToBeSavedDTO address;
}
