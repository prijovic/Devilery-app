package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.model.models.ReportStatus;
import com.ftn.sbnz.service.services.deliverer.CheckDelivererReports;
import com.ftn.sbnz.service.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeReportStatus {
    private final GetReportById getReportById;
    private final SaveReport saveReport;
    private final CheckDelivererReports checkDelivererReports;
    private final SaveUser saveUser;

    public Report execute(UUID id, ReportStatus status) {
        Report report = getReportById.execute(id);
        report.setStatus(status);
        report = saveReport.execute(report);
        Deliverer deliverer = report.getDeliverer();
        deliverer.getReports().add(report);
        deliverer = (Deliverer) saveUser.execute(deliverer);
        if (status.equals(ReportStatus.ACCEPTED)) {
            checkDelivererReports.execute(deliverer);
        }
        return report;
    }
}
