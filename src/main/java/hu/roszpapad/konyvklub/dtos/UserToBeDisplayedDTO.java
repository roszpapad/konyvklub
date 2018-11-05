package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class UserToBeDisplayedDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private AddressForEverythingDTO address;
}
