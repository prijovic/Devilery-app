package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.order.GetPriceEstimationRequest;
import com.ftn.sbnz.service.dto.request.order.NewOrderRequest;
import com.ftn.sbnz.service.dto.response.ChargeResponse;
import com.ftn.sbnz.service.dto.response.DelivererOrderResponse;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.dto.response.RestaurantOrderResponse;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final GetRestaurantActiveOrders getRestaurantActiveOrders;
    private final GetDelivererActiveOrders getDelivererActiveOrders;
    private final GetOrderPriceEstimation getOrderPriceEstimation;
    private final GetOrderByIdForRestaurant getOrderByIdForRestaurant;
    private final GetOrderByIdForDeliverer getOrderByIdForDeliverer;
    private final CreateOrder createOrder;
    private final ChangeOrderStatus changeOrderStatus;
    private final RejectOrder rejectOrder;
    private final MarkDoneOrder markDoneOrder;
    private final MarkUnsuccessfulOrder markUnsuccessfulOrder;
    private final GetRestaurantIdForSubscription getRestaurantIdForSubscription;

    @GetMapping("/price")
    public ChargeResponse getPriceForOrder(@Valid GetPriceEstimationRequest getPriceEstimationRequest) {
        return getOrderPriceEstimation.execute(getPriceEstimationRequest);
    }

    @GetMapping("/restaurant-id")
    @HasAnyPermission({Permission.READ_RESTAURANT_ORDERS})
    public UUID getRestaurantId() {
        return getRestaurantIdForSubscription.execute();
    }

    @GetMapping("/restaurant-active")
    @HasAnyPermission({Permission.READ_RESTAURANT_ORDERS})
    public List<RestaurantOrderResponse> getRestaurantsActiveOrders() {
        return getRestaurantActiveOrders.execute();
    }

    @GetMapping("/restaurant")
    @HasAnyPermission({Permission.READ_RESTAURANT_ORDERS})
    public RestaurantOrderResponse getRestaurantOrderById(@RequestParam UUID id) {
        return getOrderByIdForRestaurant.execute(id);
    }

    @GetMapping("/deliverer-active")
    @HasAnyPermission({Permission.READ_DELIVERER_ORDERS})
    public List<DelivererOrderResponse> getDeliverersActiveOrders() {
        return getDelivererActiveOrders.execute();
    }

    @GetMapping("/deliverer")
    @HasAnyPermission({Permission.READ_DELIVERER_ORDERS})
    public DelivererOrderResponse getDelivererOrderById(@RequestParam UUID id) {
        return getOrderByIdForDeliverer.execute(id);
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody NewOrderRequest newOrderRequest) {
        return createOrder.execute(newOrderRequest);
    }

    @PutMapping("/{id}/accept")
    @HasAnyPermission({Permission.ORDER_STATUS_UPDATE})
    public void acceptOrder(@NotBlank @PathVariable UUID id) {
        changeOrderStatus.execute(id, OrderStatus.ACCEPTED);
    }

    @PutMapping("/{id}/reject")
    @HasAnyPermission({Permission.ORDER_STATUS_UPDATE})
    public void rejectOrder(@NotBlank @PathVariable UUID id, @RequestParam String reason) {
        rejectOrder.execute(id, reason);
    }

    @PutMapping("/{id}/done")
    @HasAnyPermission({Permission.ORDER_STATUS_UPDATE})
    public void markDoneOrder(@NotBlank @PathVariable UUID id) {
        markDoneOrder.execute(id);
    }

    @PutMapping("/{id}/delivering")
    @HasAnyPermission({Permission.DELIVERY_ORDER_STATUS_UPDATE})
    public void markBeingDelivered(@NotBlank @PathVariable UUID id) {
        changeOrderStatus.execute(id, OrderStatus.BEING_DELIVERED);
    }

    @PutMapping("/{id}/success")
    @HasAnyPermission({Permission.DELIVERY_ORDER_STATUS_UPDATE})
    public void markSuccessfulOrder(@NotBlank @PathVariable UUID id) {
        changeOrderStatus.execute(id, OrderStatus.DELIVERED);
    }

    @PutMapping("/{id}/fail")
    @HasAnyPermission({Permission.DELIVERY_ORDER_STATUS_UPDATE})
    public void markFailedOrder(@NotBlank @PathVariable UUID id, @RequestParam String reason) {
        markUnsuccessfulOrder.execute(id, reason);
    }
}
