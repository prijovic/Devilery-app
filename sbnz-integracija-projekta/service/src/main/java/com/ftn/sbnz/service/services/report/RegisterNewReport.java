package com.ftn.sbnz.service.services.report;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.model.models.ReportStatus;
import com.ftn.sbnz.service.dto.request.report.NewReportRequest;
import com.ftn.sbnz.service.services.order.GetOrderById;
import com.ftn.sbnz.service.services.order.SaveOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class RegisterNewReport {
    private final GetOrderById getOrderById;
    private final SaveReport saveReport;
    private final SaveOrder saveOrder;

    public Report execute(NewReportRequest newReportRequest) {
        Order order = getOrderById.execute(newReportRequest.getOrderId());

        Report report = Report.builder()
                .createdOn(Calendar.getInstance().getTime())
                .status(ReportStatus.PENDING)
                .customer(order.getCustomer())
                .deliverer(order.getDeliverer())
                .comment(newReportRequest.getComment())
                .build();

        report = saveReport.execute(report);

        order.setReport(report);
        saveOrder.execute(order);

        return report;
    }
}
