package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;

public class RestaurantConverter {

    public static RestaurantResponse toRestaurantResponse(final Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .workingHours(restaurant.getWorkingHours())
                .minOrder(restaurant.getMinOrder())
                .minPrep(restaurant.getMinPrep())
                .maxPrep(restaurant.getMaxPrep())
                .isClosed(restaurant.isClosed())
                .isNew(restaurant.isNew())
                .rating(restaurant.getRating())
                .specializedTypes(restaurant.getSpecializedTypes())
                .build();
    }
}
