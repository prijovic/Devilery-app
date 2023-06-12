package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.service.services.websocket.OrderStatusChanged;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeOrderStatus {
    private final OrderStatusChanged orderStatusChanged;
    private final GetOrderById getOrderById;
    private final SaveOrder saveOrder;

    public Order execute(UUID id, OrderStatus status) {
        Order order = getOrderById.execute(id);
        order.setStatus(status);
        order = saveOrder.execute(order);
        orderStatusChanged.execute(order.getCustomer().getEmail(), order.getId(), status);
        return order;
    }
}
