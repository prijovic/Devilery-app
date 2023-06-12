package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.ReportStatus;
import com.ftn.sbnz.service.converter.ReportConverter;
import com.ftn.sbnz.service.dto.response.ReportResponse;
import com.ftn.sbnz.service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUnresolvedReports {
    private final ReportRepository reportRepository;
    
    public List<ReportResponse> execute() {
        return reportRepository.findAllByStatus(ReportStatus.PENDING)
                .stream()
                .map(ReportConverter::toReportResponse)
                .collect(Collectors.toList());
    }
}
