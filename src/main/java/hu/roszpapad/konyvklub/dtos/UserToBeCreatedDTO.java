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
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @UniqueUsername
    @Size(min = 4, max = 10)
    private String username;

    @ValidEmail
    @UniqueEmail
    private String email;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    private String passConfirm;

    @Valid
    private AddressToBeSavedDTO address;
}
