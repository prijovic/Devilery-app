package com.ftn.sbnz.service.services.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderAssignedToDeliverer {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute(String delivererEmail, UUID orderId) {
        simpMessagingTemplate.convertAndSend("/topic/deliverer-order/" + delivererEmail, orderId);
    }
}
