package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean admin;
    private Set<BookDTO> books = new HashSet<>();
    private AddressDTO address;

}
