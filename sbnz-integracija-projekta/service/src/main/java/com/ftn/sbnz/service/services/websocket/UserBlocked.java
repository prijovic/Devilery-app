package com.ftn.sbnz.service.services.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBlocked {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute() {
        simpMessagingTemplate.convertAndSend("/topic/user-blocked", "User has collected more than 3 reports and now is blocked.");
    }
}
