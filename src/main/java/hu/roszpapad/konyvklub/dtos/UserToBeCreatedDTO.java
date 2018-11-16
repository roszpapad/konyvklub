package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserToBeCreatedDTO {


    @NotBlank
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    private String passConfirm;

    private AddressToBeSavedDTO address;
}
