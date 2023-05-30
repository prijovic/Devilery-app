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
public class GetRestaurantsWithoutOwner {
    private final RestaurantRepository restaurantRepository;
    private final RunRestaurantRules runRestaurantRules;

    public List<RestaurantResponse> execute() {
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.getAllByOwnerIsNull());

        for (Restaurant restaurant : restaurants) {
            runRestaurantRules.execute(restaurant);
            RestaurantResponse restaurantResponse = RestaurantConverter.toRestaurantResponse(restaurant);
            restaurantResponses.add(restaurantResponse);
        }

        return restaurantResponses;
    }
}
