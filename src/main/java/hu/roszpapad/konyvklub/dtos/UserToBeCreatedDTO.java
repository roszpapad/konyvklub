package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.validators.annotations.PasswordsMatchConstraint;
import hu.roszpapad.konyvklub.validators.annotations.ValidEmail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordsMatchConstraint
public class UserToBeCreatedDTO {

    @NotBlank
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String username;

    @ValidEmail
    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    private String passConfirm;

    @Valid
    private AddressToBeSavedDTO address;
}
