package hu.roszpapad.konyvklub.dtos;

import lombok.Data;

@Data
public class ReportToBeDisplayedDTO {

    private Long id;

    private String reporter;

    private String reported;

    private String image;
}
