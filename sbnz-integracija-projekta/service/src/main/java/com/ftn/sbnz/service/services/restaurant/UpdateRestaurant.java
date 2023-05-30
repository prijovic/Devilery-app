package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.converter.RestaurantConverter;
import com.ftn.sbnz.service.dto.request.restaurant.RestaurantUpdateRequest;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateRestaurant {
    private final GetRestaurantById getRestaurantById;
    private final SaveRestaurant saveRestaurant;

    public RestaurantResponse execute(UUID restaurantId, RestaurantUpdateRequest restaurantUpdateRequest) {
        Restaurant restaurant = getRestaurantById.execute(restaurantId);

        restaurant.setClosed(restaurantUpdateRequest.getIsClosed());
        restaurant.setDescription(restaurantUpdateRequest.getDescription());
        restaurant.setMinOrder(restaurantUpdateRequest.getMinOrder());
        restaurant.setMinPrep(restaurantUpdateRequest.getMinPreparation());
        restaurant.setMaxPrep(restaurantUpdateRequest.getMaxPreparation());
        restaurant.getWorkingHours().setOpensAt(Time.valueOf(restaurantUpdateRequest.getOpensAt()));
        restaurant.getWorkingHours().setClosesAt(Time.valueOf(restaurantUpdateRequest.getClosesAt()));

        return RestaurantConverter.toRestaurantResponse(saveRestaurant.execute(restaurant));
    }
}
