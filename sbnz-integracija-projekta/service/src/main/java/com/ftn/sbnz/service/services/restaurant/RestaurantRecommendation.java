package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Recommendation;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.converter.RestaurantConverter;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class RestaurantRecommendation {
    static List<RestaurantResponse> getRestaurantResponses(List<RestaurantResponse> restaurantResponses, KieSession kieSession, List<Restaurant> allRestaurants) {
        for (Restaurant restaurant : allRestaurants) {
            kieSession.insert(restaurant);
        }

        kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
        kieSession.fireAllRules();

        List<Recommendation> recommendations = new ArrayList<>((Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class)));

        recommendations.sort(Comparator.comparing((Recommendation r) -> !r.getRestaurant().isClosed()).thenComparing(Recommendation::getRating).reversed());

        for (Recommendation recommendation : recommendations) {
            restaurantResponses.add(RestaurantConverter.toRestaurantResponse(recommendation.getRestaurant()));
        }

        List<Object> objects = new ArrayList<>(kieSession.getObjects(new ClassObjectFilter(Object.class)));
        for (Object object : objects) {
            kieSession.delete(kieSession.getFactHandle(object));
        }

        return restaurantResponses;
    }

    static List<RestaurantResponse> getSpecializedRestaurantResponses(KieSession kieSession, List<Restaurant> allRestaurants) {
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        getRestaurantResponses(restaurantResponses, kieSession, allRestaurants);

        return restaurantResponses;
    }
}
