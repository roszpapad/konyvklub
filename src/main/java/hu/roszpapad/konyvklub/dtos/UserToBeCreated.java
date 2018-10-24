package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserToBeCreated {

    @NotBlank
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 25)
    private String username;

    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    private String passConfirm;
}
