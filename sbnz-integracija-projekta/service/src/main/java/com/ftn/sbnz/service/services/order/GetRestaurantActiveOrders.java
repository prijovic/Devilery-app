package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.response.RestaurantOrderResponse;
import com.ftn.sbnz.service.repository.OrderRepository;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRestaurantActiveOrders {
    private final OrderRepository orderRepository;
    private final GetLoggedInUser getLoggedInUser;

    public List<RestaurantOrderResponse> execute() {
        User user = getLoggedInUser.execute();
        List<RestaurantOrderResponse> restaurantOrderResponses = new ArrayList<>();
        if (user instanceof RestaurantOwner) {
            List<Restaurant> restaurants = ((RestaurantOwner) user).getRestaurants();
            for (Restaurant restaurant : restaurants) {
                List<Order> orders = orderRepository.findAllByRestaurantIdAndStatusIn(restaurant.getId(), Arrays.asList(OrderStatus.PENDING, OrderStatus.ACCEPTED, OrderStatus.DONE));
                restaurantOrderResponses.addAll(ordersToResponses(orders));
            }
        }
        if (user instanceof RestaurantEmployee) {
            Restaurant restaurant = ((RestaurantEmployee) user).getRestaurant();
            List<Order> orders = orderRepository.findAllByRestaurantIdAndStatusIn(restaurant.getId(), Arrays.asList(OrderStatus.PENDING, OrderStatus.ACCEPTED, OrderStatus.DONE));
            restaurantOrderResponses.addAll(ordersToResponses(orders));
        }
        return restaurantOrderResponses;
    }

    private List<RestaurantOrderResponse> ordersToResponses(List<Order> orders) {
        List<RestaurantOrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(OrderConverter.toRestaurantOrderResponse(order));
        }
        return orderResponses;
    }
}
