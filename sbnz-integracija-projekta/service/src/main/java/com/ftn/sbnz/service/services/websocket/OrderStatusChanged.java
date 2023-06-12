package com.ftn.sbnz.service.services.websocket;

import com.ftn.sbnz.model.models.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderStatusChanged {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute(String email, UUID orderId, OrderStatus status) {
        simpMessagingTemplate.convertAndSend("/topic/order-status/" + email, orderId + " " + status.name());
    }
}
