package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.Review;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantTest {
    LocalDateTime now = LocalDateTime.now();

    protected KieSession initKieSession(LocalDateTime now) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("restaurantKieSession");
        kieSession.setGlobal("now", now);
        return kieSession;
    }

    protected Restaurant getNewRestaurant() {
        LocalDateTime oneMonthAgo = now.minusDays(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(oneMonthAgo);
        return restaurant;
    }

    protected Restaurant getPopularRestaurantByReviewNumber() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(now.minusMonths(2));
        for (int i = 0; i < 20; i++) {
            Review review = new Review();
            review.setRestaurantRating(5.0);
            restaurant.getReviews().add(review);
        }
        return restaurant;
    }

    protected Restaurant getPopularNewRestaurant() {
        Restaurant restaurant = getNewRestaurant();
        for (int i = 0; i < 10; i++) {
            Review review = new Review();
            review.setRestaurantRating(5.0);
            restaurant.getReviews().add(review);
        }
        return restaurant;
    }

    protected List<Review> getRatedReviews(double minRate, double maxRate, int number) {
        List<Review> reviews = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Review review = new Review();
            double randomNumber = minRate + random.nextDouble() * (maxRate - minRate);
            review.setRestaurantRating(randomNumber);
            reviews.add(review);
        }
        return reviews;
    }

    protected Restaurant getRatedRestaurant(LocalDateTime createdOn, double minRate, double maxRate, int number) {
        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(createdOn);
        restaurant.setReviews(getRatedReviews(minRate, maxRate, number));
        return restaurant;
    }
}
