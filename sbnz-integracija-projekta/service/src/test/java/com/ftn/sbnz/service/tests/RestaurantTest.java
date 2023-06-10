package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.Review;
import com.ftn.sbnz.model.models.WorkingHours;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class RestaurantTest {
    LocalDateTime now = LocalDateTime.now();

    protected Restaurant getNewRestaurant() {
        Date currentDate = new Date();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // One day in milliseconds
        Date oneDayAgo = new Date(currentDate.getTime() - oneDayInMillis);

        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(oneDayAgo);
        return restaurant;
    }

    protected Restaurant getPopularRestaurantByReviewNumber() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        Date twoMonthsAgo = calendar.getTime();
        Restaurant restaurant = new Restaurant();
        restaurant.setCreatedOn(twoMonthsAgo);
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
        restaurant.setCreatedOn(Date.from(createdOn.atZone(ZoneId.systemDefault()).toInstant()));
        restaurant.setReviews(getRatedReviews(minRate, maxRate, number));
        return restaurant;
    }

    protected Restaurant createRestaurant(String name, String opensAt, String closesAt) {
        WorkingHours workingHours = WorkingHours.builder()
                .opensAt(Time.valueOf(opensAt))
                .closesAt(Time.valueOf(closesAt))
                .build();
        Restaurant restaurant = getRatedRestaurant(LocalDateTime.now(), 1, 5, 20);
        restaurant.setId(UUID.randomUUID());
        restaurant.setWorkingHours(workingHours);
        restaurant.setName(name);

        System.out.println("Restaurant " + name + "(" + restaurant.isClosed() + ")" + " has rating " + restaurant.getRating());

        return restaurant;
    }
}
