package com.ftn.sbnz.service.services.owner;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.service.services.restaurant.GetRestaurantById;
import com.ftn.sbnz.service.services.restaurant.SaveRestaurant;
import com.ftn.sbnz.service.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddRestaurantsToOwner {
    private final SaveUser saveOwner;
    private final SaveRestaurant saveRestaurant;
    private final GetRestaurantById getRestaurantById;

    public RestaurantOwner execute(final RestaurantOwner restaurantOwner, List<UUID> restaurantsToAdd) {
        for (UUID restaurantId : restaurantsToAdd) {
            Restaurant restaurant = getRestaurantById.execute(restaurantId);
            restaurantOwner.getRestaurants().add(restaurant);
            restaurant.setOwner(restaurantOwner);
            saveRestaurant.execute(restaurant);
        }
        return (RestaurantOwner) saveOwner.execute(restaurantOwner);
    }

}
