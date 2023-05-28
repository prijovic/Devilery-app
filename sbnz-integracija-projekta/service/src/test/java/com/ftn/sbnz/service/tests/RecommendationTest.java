package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.Recommendation;
import com.ftn.sbnz.model.models.Restaurant;
import org.drools.core.ClassObjectFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationTest extends RestaurantTest {

    @Test
    public void testNewRestaurantRule() {
        Restaurant restaurant = getNewRestaurant();

        KieSession kieSession = initKieSession(now);
        kieSession.insert(restaurant);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertTrue(restaurant.isNew());
    }

    @Test
    public void testPopularRestaurantRule() {
        Restaurant restaurant1 = getPopularRestaurantByReviewNumber();
        Restaurant restaurant2 = getPopularNewRestaurant();

        KieSession kieSession = initKieSession(now);
        kieSession.insert(restaurant1);
        kieSession.insert(restaurant2);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertTrue(restaurant1.isPopular());
        assertTrue(restaurant2.isPopular());
    }

    @Test
    public void testWellRatedRestaurantRule() {
        Restaurant restaurant = getRatedRestaurant(now.minusMonths(2), 4.5, 5, 10);

        KieSession kieSession = initKieSession(now);
        kieSession.insert(restaurant);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertTrue(restaurant.isWellRated());
        assertFalse(restaurant.isBadRated());
    }

    @Test
    public void testBadlyRatedRestaurantRule() {
        Restaurant restaurant = getRatedRestaurant(now.minusMonths(2), 1, 2.49, 10);

        KieSession kieSession = initKieSession(now);
        kieSession.insert(restaurant);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertTrue(restaurant.isBadRated());
        assertFalse(restaurant.isWellRated());
    }

    @Test
    public void testRestaurantRecommendation() {
        Restaurant restaurant1 = getRatedRestaurant(now.minusMonths(2), 2.5, 5, 20);
        restaurant1.setId(UUID.randomUUID());
        Restaurant restaurant2 = getRatedRestaurant(now.minusMonths(2), 1, 2.49, 20);
        restaurant2.setId(UUID.randomUUID());
        Restaurant restaurant3 = getNewRestaurant();
        restaurant3.setId(UUID.randomUUID());

        KieSession kieSession = initKieSession(now);
        kieSession.insert(restaurant1);
        kieSession.insert(restaurant2);
        kieSession.insert(restaurant3);

        kieSession.fireAllRules();

        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));

        kieSession.dispose();

        assertEquals(2, recommendations.size());
    }

    @Test
    public void testRedundantRestaurantRecommendationRemoval() {
        KieSession kieSession = initKieSession(now);
        List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Restaurant restaurant = getRatedRestaurant(now.minusMonths(2), 2.5, 5, 20);
            restaurant.setId(UUID.randomUUID());
            kieSession.insert(restaurant);
            restaurants.add(restaurant);
        }
        kieSession.fireAllRules();

        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));

        kieSession.dispose();

        restaurants.sort(Comparator.comparing(Restaurant::getRating).reversed());
        List<Restaurant> top10Restaurants = restaurants.subList(0, 10);

        assertEquals(10, recommendations.size());
        for (Recommendation recommendation : recommendations) {
            assertTrue(top10Restaurants.contains(recommendation.getRestaurant()));
        }
    }


}
