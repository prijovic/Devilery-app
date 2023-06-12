package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.model.models.ReportStatus;
import com.ftn.sbnz.service.dto.request.report.NewReportRequest;
import com.ftn.sbnz.service.dto.response.ReportResponse;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.report.ChangeReportStatus;
import com.ftn.sbnz.service.services.report.GetUnresolvedReports;
import com.ftn.sbnz.service.services.report.RegisterNewReport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final RegisterNewReport registerNewReport;
    private final GetUnresolvedReports getUnresolvedReports;
    private final ChangeReportStatus changeReportStatus;

    @PostMapping
    public void createReport(@RequestBody NewReportRequest newReportRequest) {
        registerNewReport.execute(newReportRequest);
    }

    @GetMapping("/pending")
    @HasAnyPermission({Permission.REPORT_MANAGEMENT})
    public List<ReportResponse> getAllUnresolvedReports() {
        return getUnresolvedReports.execute();
    }

    @PutMapping("/{id}/status")
    @HasAnyPermission({Permission.REPORT_MANAGEMENT})
    public void changeReportStatus(@PathVariable UUID id, @RequestParam ReportStatus status) {
        changeReportStatus.execute(id, status);
    }
}
