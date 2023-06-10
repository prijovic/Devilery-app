package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.response.DelivererOrderResponse;
import com.ftn.sbnz.service.repository.OrderRepository;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDelivererActiveOrders {
    private final OrderRepository orderRepository;
    private final GetLoggedInUser getLoggedInUser;

    public List<DelivererOrderResponse> execute() {
        Deliverer deliverer = (Deliverer) getLoggedInUser.execute();
        List<DelivererOrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByDelivererAndStatusIn(deliverer, Arrays.asList(OrderStatus.DONE, OrderStatus.BEING_DELIVERED));
        for (Order order : orders) {
            orderResponses.add(OrderConverter.toDelivererOrderResponse(order));
        }
        return orderResponses;
    }
}
