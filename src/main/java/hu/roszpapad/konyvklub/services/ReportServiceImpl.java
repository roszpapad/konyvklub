package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.ReportToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Report;
import hu.roszpapad.konyvklub.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    private final UserService userService;


    @Override
    public Report createReport(ReportToBeCreatedDTO reportDTO) {
        Report report = new Report();
        report.setImage(reportDTO.getImage());
        report.setReported(reportDTO.getReported());
        report.setReporter(reportDTO.getReporter());
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getReportsByReported(String reported) {
        List<Report> reports = reportRepository.findByReported(reported);
        return reports.stream()
                .filter(report -> userService.isActive(report.getReported()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .filter(report -> userService.isActive(report.getReported()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReportsByReported(String reported) {
        List<Report> reports = getReportsByReported(reported);
        reportRepository.deleteAll(reports);
    }
}
