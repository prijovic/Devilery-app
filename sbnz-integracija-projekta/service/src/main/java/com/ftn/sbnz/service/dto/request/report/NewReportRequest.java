package com.ftn.sbnz.service.dto.request.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class NewReportRequest {
    @NotEmpty
    UUID orderId;
    String comment;
}
