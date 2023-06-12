package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.service.dto.response.ReportResponse;

public class ReportConverter {

    public static ReportResponse toReportResponse(Report report) {
        return ReportResponse.builder()
                .id(report.getId())
                .createdOn(report.getCreatedOn())
                .status(report.getStatus())
                .customer(UserConverter.toUserResponse(report.getCustomer()))
                .deliverer(DelivererConverter.toOrderDelivererResponse(report.getDeliverer()))
                .comment(report.getComment())
                .build();
    }
}
