package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.MenuItemType;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ftn.sbnz.service.services.restaurant.RestaurantRecommendation.getSpecializedRestaurantResponses;

@Service
@RequiredArgsConstructor
public class GetSpecializedRestaurantForType {
    private final KieSession kieSession;
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> execute(MenuItemType type) {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        kieSession.insert(type);
        return getSpecializedRestaurantResponses(kieSession, allRestaurants);
    }
}
