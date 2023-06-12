package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.service.exception.ReportNotFoundException;
import com.ftn.sbnz.service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetReportById {
    private final ReportRepository reportRepository;

    public Report execute(UUID id) {
        return reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
    }
}
