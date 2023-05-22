package com.ftn.sbnz.service.tests;
import com.ftn.sbnz.model.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.Recommendation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationTest {

    private KieSession kieSession;

    @BeforeEach
    public void setup() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        this.kieSession = kContainer.newKieSession("recommendationKieSession");
    }

    @Test
    public void testNewRestaurantRule() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(oneMonthAgo);

        kieSession.setGlobal("now", now);
        kieSession.insert(restaurant);
        kieSession.fireAllRules();

        assertTrue(restaurant.isNew());
    }

    @Test
    public void testPopularRestaurantRule() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(LocalDateTime.now());
        restaurant.setReviews(new ArrayList<>());

        for (int i = 0; i < 20; i++) {
            restaurant.getReviews().add(new Review());
        }

        kieSession.insert(restaurant);
        kieSession.fireAllRules();

        assertTrue(restaurant.isPopular());
    }

    @Test
    public void testWellRatedRestaurantRule() {
        Restaurant restaurant = new Restaurant();
        Review review = new Review();
        review.setRestaurantRating(5.0);

        kieSession.insert(restaurant);
        kieSession.fireAllRules();

        assertTrue(restaurant.isTopRated());
    }

    @Test
    public void testBadlyRatedRestaurantRule() {
        Restaurant restaurant = new Restaurant();
        Review review = new Review();
        review.setRestaurantRating(5.0);

        kieSession.insert(restaurant);
        kieSession.fireAllRules();

        assertTrue(restaurant.isBadRated());
    }
}
