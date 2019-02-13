package hu.roszpapad.konyvklub.dtos;

import hu.roszpapad.konyvklub.validators.annotations.UsernameExists;
import lombok.Data;

@Data
public class ReportToBeCreatedDTO {

    private String reporter;

    @UsernameExists
    private String reported;

    private String image;
}
