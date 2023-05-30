package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.dto.response.OrderResponse;

public class OrderConverter {

    public static OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .createdOn(order.getCreatedOn())
                .deliverer(DelivererConverter.toOrderDelivererResponse(order.getDeliverer()))
                .customer(UserConverter.toUserResponse(order.getCustomer()))
                .status(order.getStatus())
                .deliveryDistance(order.getDeliveryDistance())
                .discount(order.getDiscount())
                .tip(order.getTip())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .build();
    }
}
