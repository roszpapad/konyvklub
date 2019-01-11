package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressToBeSavedDTO {

    @NotNull
    private String city;

    private String street;

    private String number;
}
