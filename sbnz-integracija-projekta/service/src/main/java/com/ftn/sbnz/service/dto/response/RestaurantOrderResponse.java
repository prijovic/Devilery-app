package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOrderResponse {
    UUID id;
    OrderStatus status;
    List<MenuItemResponse> items;
    ChargeResponse charge;
}
