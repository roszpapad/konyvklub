package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.validators.annotations.ValidPassword;
import lombok.Data;

@Data
@ValidPassword
public class ChangePasswordRequestDTO {

    private String password;

    private String username;
}
