package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.dto.response.OrderDelivererResponse;
import com.ftn.sbnz.service.dto.response.OrderResponse;

public class OrderConverter {

    public static OrderResponse toOrderResponse(Order order) {
        OrderDelivererResponse deliverer = null;
        if (order.getDeliverer() != null) {
            deliverer = DelivererConverter.toOrderDelivererResponse(order.getDeliverer());
        }
        return OrderResponse.builder()
                .id(order.getId())
                .createdOn(order.getCreatedOn())
                .deliverer(deliverer)
                .customer(UserConverter.toUserResponse(order.getCustomer()))
                .status(order.getStatus())
                .charge(ChargeConverter.toChargeResponse(order.getCharge()))
                .deliveryDistance(order.getDeliveryDistance())
                .address(order.getAddress())
                .build();
    }
}
