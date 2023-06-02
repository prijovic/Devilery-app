package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Recommendation;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.converter.RestaurantConverter;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.List;

public class RestaurantRecommendation {
    static List<RestaurantResponse> getRestaurantResponses(List<RestaurantResponse> restaurantResponses, KieSession kieSession, List<Restaurant> allRestaurants) {
        for (Restaurant restaurant : allRestaurants) {
            kieSession.insert(restaurant);
        }

        kieSession.fireAllRules();

        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));

        for (Recommendation recommendation : recommendations) {
            restaurantResponses.add(RestaurantConverter.toRestaurantResponse(recommendation.getRestaurant()));
        }

        kieSession.dispose();

        return restaurantResponses;
    }

}
