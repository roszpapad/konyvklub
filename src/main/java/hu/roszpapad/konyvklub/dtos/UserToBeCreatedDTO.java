package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.validators.annotations.PasswordsMatchConstraint;
import hu.roszpapad.konyvklub.validators.annotations.UniqueEmail;
import hu.roszpapad.konyvklub.validators.annotations.UniqueUsername;
import hu.roszpapad.konyvklub.validators.annotations.ValidEmail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordsMatchConstraint
public class UserToBeCreatedDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @UniqueUsername
    private String username;

    @ValidEmail
    @UniqueEmail
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String password;

    private String passConfirm;

    @Valid
    private AddressToBeSavedDTO address;
}
