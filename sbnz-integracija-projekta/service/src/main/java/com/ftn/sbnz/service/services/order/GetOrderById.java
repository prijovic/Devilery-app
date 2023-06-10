package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.exception.OrderNotFound;
import com.ftn.sbnz.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderById {
    private final OrderRepository orderRepository;

    public Order execute(UUID id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFound::new);
    }
}
