package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.ReportToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Report;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportToBeDisplayedDTOConverter {

    private final ModelMapper modelMapper;

    public ReportToBeDisplayedDTO toDTO(Report entity) {
        return modelMapper.map(entity, ReportToBeDisplayedDTO.class);
    }

    public Report toEntity(ReportToBeDisplayedDTO dto) {
        return modelMapper.map(dto, Report.class);
    }
}
