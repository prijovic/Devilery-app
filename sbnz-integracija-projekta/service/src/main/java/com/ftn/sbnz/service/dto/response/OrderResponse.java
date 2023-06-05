package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.model.models.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    UUID id;
    LocalDateTime createdOn;
    OrderDelivererResponse deliverer;
    UserResponse customer;
    OrderStatus status;
    Double deliveryDistance;
    ChargeResponse charge;
    Address address;
}
