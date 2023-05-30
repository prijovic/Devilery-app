package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.request.order.NewOrderRequest;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.services.address.GetAddressById;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import com.ftn.sbnz.service.services.user.CalculateDiscount;
import com.ftn.sbnz.service.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrder {
    private final GetLoggedInUser getLoggedInUser;
    private final GetAddressById getAddressById;
    private final CalculateDiscount calculateDiscount;
    private final CalculateOrderPrice calculateOrderPrice;
    private final SaveOrder saveOrder;
    private final SaveUser saveUser;

    public OrderResponse execute(NewOrderRequest newOrderRequest) {
        User customer = getLoggedInUser.execute();
        calculateDiscount.execute(customer);

        Order order = Order.builder()
                .createdOn(LocalDateTime.now())
                .customer(customer)
                .status(OrderStatus.PENDING)
                .deliveryDistance(newOrderRequest.getDeliveryDistance())
                .discount(customer.getDiscount())
                .tip(newOrderRequest.getTip())
                .address(getAddressById.execute(newOrderRequest.getAddressId()))
                .build();

        calculateOrderPrice.execute(order);
        order = saveOrder.execute(order);
        customer.getOrders().add(order);
        saveUser.execute(customer);

        return OrderConverter.toOrderResponse(order);
    }


}
