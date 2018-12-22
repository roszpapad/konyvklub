package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressToBeSavedDTO {

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String number;
}
