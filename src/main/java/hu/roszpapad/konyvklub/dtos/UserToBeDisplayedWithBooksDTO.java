package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserToBeDisplayedWithBooksDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private AddressForEverythingDTO address;

    private List<BookToBeDisplayedDTO> books = new ArrayList<>();
}
