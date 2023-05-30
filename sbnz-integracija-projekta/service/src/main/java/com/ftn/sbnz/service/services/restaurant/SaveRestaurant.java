package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveRestaurant {
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = false)
    public Restaurant execute(final Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
