package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.converter.RestaurantConverter;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllRestaurants {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> execute() {
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        for (Restaurant restaurant : allRestaurants) {
            restaurantResponses.add(RestaurantConverter.toRestaurantResponse(restaurant));
        }

        return restaurantResponses;
    }
}
