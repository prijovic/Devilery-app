package com.ftn.sbnz.service.services.order;


import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.response.RestaurantOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderByIdForRestaurant {
    private final GetOrderById getOrderById;

    public RestaurantOrderResponse execute(UUID id) {
        return OrderConverter.toRestaurantOrderResponse(getOrderById.execute(id));
    }
}
