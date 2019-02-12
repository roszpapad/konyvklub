package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.ReportToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.ReportToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Report;
import hu.roszpapad.konyvklub.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final Converter<Report, ReportToBeDisplayedDTO> reportToBeDisplayedDTOConverter;

    @PostMapping("/reports/create")
    public ResponseEntity<ReportToBeDisplayedDTO> createReport(@RequestBody ReportToBeCreatedDTO reportDTO){
        Report report = reportService.createReport(reportDTO);
        return ResponseEntity.ok(reportToBeDisplayedDTOConverter.toDTO(report));
    }

    @GetMapping("/reports/filtered")
    public ResponseEntity<List<ReportToBeDisplayedDTO>> listReportsByReported(@PathParam(value = "reported") String reported){

        List<Report> reports = reportService.getTicketsByReported(reported);
        List<ReportToBeDisplayedDTO> reportDTOs = new ArrayList<>();
        reports.forEach(report -> reportDTOs.add(reportToBeDisplayedDTOConverter.toDTO(report)));
        return ResponseEntity.ok(reportDTOs);
    }
}
