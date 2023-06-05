package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.request.order.GetPriceEstimationRequest;
import com.ftn.sbnz.service.dto.request.order.NewOrderRequest;
import com.ftn.sbnz.service.dto.response.ChargeResponse;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.services.order.CreateOrder;
import com.ftn.sbnz.service.services.order.GetOrderPriceEstimation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final GetOrderPriceEstimation getOrderPriceEstimation;
    private final CreateOrder createOrder;

    @GetMapping("/price")
    public ChargeResponse getPriceForOrder(@Valid GetPriceEstimationRequest getPriceEstimationRequest) {
        return getOrderPriceEstimation.execute(getPriceEstimationRequest);
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody NewOrderRequest newOrderRequest) {
        return createOrder.execute(newOrderRequest);
    }

    @PutMapping("/{id}/confirm")
    public void confirmOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/decline")
    public void declineOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/success")
    public void markSuccessfulOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/fail")
    public void markFailedOrder(@NotBlank @PathVariable UUID id) {
    }
}
