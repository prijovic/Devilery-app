package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.request.order.NewOrderRequest;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.services.address.GetAddressById;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import com.ftn.sbnz.service.services.charge.SaveCharge;
import com.ftn.sbnz.service.services.menuItem.GetMenuItemsById;
import com.ftn.sbnz.service.services.restaurant.SaveRestaurant;
import com.ftn.sbnz.service.services.user.CalculateDiscount;
import com.ftn.sbnz.service.services.user.SaveUser;
import com.ftn.sbnz.service.services.websocket.NewOrderArrivedToRestaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrder {
    private final GetLoggedInUser getLoggedInUser;
    private final GetAddressById getAddressById;
    private final GetMenuItemsById getMenuItemsById;
    private final CalculateDiscount calculateDiscount;
    private final CalculateOrderPrice calculateOrderPrice;
    private final SaveOrder saveOrder;
    private final SaveCharge saveCharge;
    private final SaveRestaurant saveRestaurant;
    private final SaveUser saveUser;
    private final NewOrderArrivedToRestaurant newOrderArrivedToRestaurant;

    public OrderResponse execute(NewOrderRequest newOrderRequest) {
        User customer = getLoggedInUser.execute();
        calculateDiscount.execute(customer);

        List<MenuItem> menuItems = getMenuItemsById.execute(newOrderRequest.getItemIds());
        Restaurant restaurant = menuItems.get(0).getRestaurant();
        Order order = Order.builder()
                .createdOn(LocalDateTime.now())
                .customer(customer)
                .status(OrderStatus.PENDING)
                .items(menuItems)
                .deliveryDistance(newOrderRequest.getDeliveryDistance())
                .address(getAddressById.execute(newOrderRequest.getAddressId()))
                .restaurant(restaurant)
                .build();

        calculateOrderPrice.execute(order);

        order.setCharge(saveCharge.execute(order.getCharge()));

        order = saveOrder.execute(order);

        restaurant.getOrders().add(order);
        saveRestaurant.execute(restaurant);

        customer.getOrders().add(order);
        saveUser.execute(customer);

        newOrderArrivedToRestaurant.execute(restaurant.getId(), order.getId());

        return OrderConverter.toOrderResponse(order);
    }
}
