package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.model.models.WorkingHours;
import com.ftn.sbnz.service.dto.request.restaurant.RestaurantRegistrationRequest;
import com.ftn.sbnz.service.services.address.SaveAddress;
import com.ftn.sbnz.service.services.owner.AddRestaurantsToOwner;
import com.ftn.sbnz.service.services.user.GetUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterNewRestaurant {
    private final GetUserById getUserById;
    private final SaveRestaurant saveRestaurant;
    private final AddRestaurantsToOwner addRestaurantsToOwner;
    private final SaveAddress saveAddress;

    public Restaurant execute(RestaurantRegistrationRequest restaurantRegistrationRequest) {
        Address address = Address.builder()
                .name(restaurantRegistrationRequest.getAddressName())
                .longitude(restaurantRegistrationRequest.getLongitude())
                .latitude(restaurantRegistrationRequest.getLatitude())
                .build();
        saveAddress.execute(address);

        WorkingHours workingHours = WorkingHours.builder()
                .opensAt(Time.valueOf(restaurantRegistrationRequest.getOpensAt()))
                .closesAt(Time.valueOf(restaurantRegistrationRequest.getClosesAt()))
                .build();

        Restaurant restaurant = Restaurant.builder()
                .createdOn(LocalDateTime.now())
                .name(restaurantRegistrationRequest.getName())
                .description(restaurantRegistrationRequest.getDescription())
                .address(address)
                .createdOn(LocalDateTime.now())
                .minOrder(restaurantRegistrationRequest.getMinOrder() == null ? 0 : restaurantRegistrationRequest.getMinOrder())
                .minPrep(restaurantRegistrationRequest.getMinPreparation())
                .maxPrep(restaurantRegistrationRequest.getMaxPreparation())
                .picture(restaurantRegistrationRequest.getPicture())
                .closed(true)
                .workingHours(workingHours)
                .build();

        restaurant = saveRestaurant.execute(restaurant);

        if (restaurantRegistrationRequest.getOwnerId() != null) {
            RestaurantOwner owner = (RestaurantOwner) getUserById.execute(restaurantRegistrationRequest.getOwnerId());
            restaurant.setOwner(owner);
            restaurant = saveRestaurant.execute(restaurant);
            addRestaurantsToOwner.execute(owner, new ArrayList<>(Collections.singleton(restaurant.getId())));
        }

        return restaurant;
    }
}
