package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveReport {
    private final ReportRepository reportRepository;

    public Report execute(Report report) {
        return reportRepository.save(report);
    }
}
