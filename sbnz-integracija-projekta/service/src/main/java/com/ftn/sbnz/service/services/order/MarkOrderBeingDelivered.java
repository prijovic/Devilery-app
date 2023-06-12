package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.service.services.websocket.OrderPickedUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkOrderBeingDelivered {
    private final OrderPickedUp orderPickedUp;
    private final ChangeOrderStatus changeOrderStatus;

    public Order execute(UUID id) {
        Order order = changeOrderStatus.execute(id, OrderStatus.BEING_DELIVERED);
        this.orderPickedUp.execute(id);
        return order;
    }
}
