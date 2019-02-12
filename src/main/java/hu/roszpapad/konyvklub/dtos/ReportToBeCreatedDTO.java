package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class ReportToBeCreatedDTO {

    private String reporter;

    private String reported;

    private String image;
}
