package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.model.models.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeReportStatus {
    private final GetReportById getReportById;
    private final SaveReport saveReport;

    public Report execute(UUID id, ReportStatus status) {
        Report report = getReportById.execute(id);
        report.setStatus(status);
        return saveReport.execute(report);
    }
}
