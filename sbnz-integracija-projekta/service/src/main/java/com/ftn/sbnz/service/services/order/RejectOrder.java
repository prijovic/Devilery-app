package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RejectOrder {
    private final ChangeOrderStatus changeOrderStatus;
    private final SaveOrder saveOrder;

    public Order execute(UUID id, String reason) {
        Order order = changeOrderStatus.execute(id, OrderStatus.REJECTED);
        order.setRejectionReason(reason);
        return saveOrder.execute(order);
    }
}
