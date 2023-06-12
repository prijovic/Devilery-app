package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.ReportStatus;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {
    UUID id;
    Date createdOn;
    ReportStatus status;
    UserResponse customer;
    OrderDelivererResponse deliverer;
    String comment;
}
