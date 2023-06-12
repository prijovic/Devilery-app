package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.model.models.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findAllByStatus(ReportStatus status);
}