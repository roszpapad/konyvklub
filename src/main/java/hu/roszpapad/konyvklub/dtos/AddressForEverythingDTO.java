package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class AddressForEverythingDTO {

    private Long id;
    private String city;
    private String street;
    private String number;
}
