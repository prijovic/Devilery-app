package com.ftn.sbnz.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping
    public void createOrder() {
    }

    @PutMapping("/{id}/confirm")
    public void confirmOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/confirm")
    public void declineOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/success")
    public void markSuccessfulOrder(@NotBlank @PathVariable UUID id) {
    }

    @PutMapping("/{id}/fail")
    public void markFailedOrder(@NotBlank @PathVariable UUID id) {
    }
}
