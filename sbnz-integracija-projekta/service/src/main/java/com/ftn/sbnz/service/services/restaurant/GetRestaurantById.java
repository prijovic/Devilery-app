package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.exception.RestaurantNotFoundException;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRestaurantById {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Restaurant execute(final UUID id) {
        return restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }
}
