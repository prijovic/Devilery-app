package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.model.models.OrderStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DelivererOrderResponse {
    UUID id;
    OrderStatus status;
    ChargeResponse charge;
    RestaurantResponse restaurant;
    UserResponse customer;
    Address address;
}
