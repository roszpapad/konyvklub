package hu.roszpapad.konyvklub.services;


import hu.roszpapad.konyvklub.dtos.ReportToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.Report;

import java.util.List;

public interface ReportService {

    Report createReport(ReportToBeCreatedDTO reportDTO);
    List<Report> getReportsByReported(String reported);
    List<Report> getAllReports();
    void deleteReportsByReported(String reported);
}
