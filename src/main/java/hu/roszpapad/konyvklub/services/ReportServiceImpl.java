package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.ReportToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Report;
import hu.roszpapad.konyvklub.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    @Override
    public Report createReport(ReportToBeCreatedDTO reportDTO) {
        Report report = new Report();
        report.setImage(reportDTO.getImage());
        report.setReported(reportDTO.getReported());
        report.setReporter(reportDTO.getReporter());
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getTicketsByReported(String reported) {
        return reportRepository.findByReported(reported);
    }
}
