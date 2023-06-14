package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkDoneOrder {
    private final ChangeOrderStatus changeOrderStatus;
    private final AssignDelivererToOrder assignDelivererToOrder;

    public Order execute(UUID id) {
        Order order = changeOrderStatus.execute(id, OrderStatus.DONE);
        assignDelivererToOrder.execute(order);
        return order;
    }
}
