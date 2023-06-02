package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.RestaurantEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddEmployeeToRestaurant {
    private final GetRestaurantById getRestaurantById;
    private final SaveRestaurant saveRestaurant;

    public Restaurant execute(UUID restaurantId, RestaurantEmployee restaurantEmployee) {
        Restaurant restaurant = getRestaurantById.execute(restaurantId);
        restaurant.getEmployees().add(restaurantEmployee);
        return saveRestaurant.execute(restaurant);
    }
}
