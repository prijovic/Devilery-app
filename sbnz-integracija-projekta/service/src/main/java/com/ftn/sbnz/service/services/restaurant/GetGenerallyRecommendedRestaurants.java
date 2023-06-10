package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ftn.sbnz.service.services.restaurant.RestaurantRecommendation.getRestaurantResponses;

@Service
@RequiredArgsConstructor
public class GetGenerallyRecommendedRestaurants {
    private final KieSession kieSession;
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> execute() {
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        return getRestaurantResponses(restaurantResponses, kieSession, allRestaurants);
    }


}
