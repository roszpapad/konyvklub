package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class ChangingPasswordDTO {

    private String token;

    private String password;
}
