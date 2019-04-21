package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.ReportToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.ReportToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.ReportToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Report;
import hu.roszpapad.konyvklub.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final ReportToBeDisplayedDTOConverter reportToBeDisplayedDTOConverter;

    @PostMapping("/reports/create")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<ReportToBeDisplayedDTO> createReport(@Valid @RequestBody ReportToBeCreatedDTO reportDTO){
        Report report = reportService.createReport(reportDTO);
        return ResponseEntity.ok(reportToBeDisplayedDTOConverter.toDTO(report));
    }

    @GetMapping("/reports/filtered")
    @PreAuthorize("hasRole('KONYVKLUB_ADMIN')")
    public ResponseEntity<List<ReportToBeDisplayedDTO>> listReportsByReported(@PathParam(value = "reported") String reported){

        List<Report> reports = reportService.getReportsByReported(reported);
        List<ReportToBeDisplayedDTO> reportDTOs = new ArrayList<>();
        reports.forEach(report -> reportDTOs.add(reportToBeDisplayedDTOConverter.toDTO(report)));
        return ResponseEntity.ok(reportDTOs);
    }

    @DeleteMapping("/reports/{reported}/delete")
    @PreAuthorize("hasRole('KONYVKLUB_ADMIN')")
    public ResponseEntity<String> deleteReportsByReported(@PathVariable(value = "reported") String reported){

        reportService.deleteReportsByReported(reported);
        return ResponseEntity.ok("Reportok törölve.");
    }

    @GetMapping("/reports/all")
    @PreAuthorize("hasRole('KONYVKLUB_ADMIN')")
    public ResponseEntity<List<ReportToBeDisplayedDTO>> listReports(){

        List<Report> reports = reportService.getAllReports();
        List<ReportToBeDisplayedDTO> reportDTOs = new ArrayList<>();
        reports.forEach(report -> reportDTOs.add(reportToBeDisplayedDTOConverter.toDTO(report)));
        return ResponseEntity.ok(reportDTOs);
    }
}
