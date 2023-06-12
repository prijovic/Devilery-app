package com.ftn.sbnz.service.services.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderPickedUp {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute(UUID orderId) {
        simpMessagingTemplate.convertAndSend("/topic/order-picked-up", orderId);
    }
}
