package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class AddressForEverything {

    private Long id;
    private String city;
    private String street;
    private String number;
}
