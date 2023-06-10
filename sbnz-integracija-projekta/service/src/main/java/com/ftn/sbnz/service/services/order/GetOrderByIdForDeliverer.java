package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.response.DelivererOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderByIdForDeliverer {
    private final GetOrderById getOrderById;

    public DelivererOrderResponse execute(UUID id) {
        return OrderConverter.toDelivererOrderResponse(getOrderById.execute(id));
    }
}
