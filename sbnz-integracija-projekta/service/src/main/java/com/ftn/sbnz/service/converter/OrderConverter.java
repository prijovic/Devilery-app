package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.dto.response.DelivererOrderResponse;
import com.ftn.sbnz.service.dto.response.OrderDelivererResponse;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.dto.response.RestaurantOrderResponse;

import java.util.stream.Collectors;

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
                .rejectionReason(order.getRejectionReason())
                .unsuccessfulDeliveryReason(order.getUnsuccessfulDeliveryReason())
                .items(order.getItems().stream().map(MenuItemConverter::toMenuItemResponse).collect(Collectors.toList()))
                .restaurant(RestaurantConverter.toRestaurantResponse(order.getRestaurant()))
                .charge(ChargeConverter.toChargeResponse(order.getCharge()))
                .deliveryDistance(order.getDeliveryDistance())
                .address(order.getAddress())
                .isReportSubmitted(order.getReport() != null)
                .build();
    }

    public static RestaurantOrderResponse toRestaurantOrderResponse(Order order) {
        return RestaurantOrderResponse.builder()
                .id(order.getId())
                .charge(ChargeConverter.toChargeResponse(order.getCharge()))
                .status(order.getStatus())
                .items(order.getItems().stream().map(MenuItemConverter::toMenuItemResponse).collect(Collectors.toList()))
                .build();
    }

    public static DelivererOrderResponse toDelivererOrderResponse(Order order) {
        return DelivererOrderResponse.builder()
                .id(order.getId())
                .charge(ChargeConverter.toChargeResponse(order.getCharge()))
                .status(order.getStatus())
                .address(order.getAddress())
                .customer(UserConverter.toUserResponse(order.getCustomer()))
                .restaurant(RestaurantConverter.toRestaurantResponse(order.getRestaurant()))
                .build();
    }
}
