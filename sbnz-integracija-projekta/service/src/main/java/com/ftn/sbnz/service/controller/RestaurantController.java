package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.MenuItemType;
import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.restaurant.RestaurantRegistrationRequest;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.restaurant.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RegisterNewRestaurant registerNewRestaurant;
    private final GetRestaurantsWithoutOwner getRestaurantsWithoutOwner;
    private final GetAllRestaurants getAllRestaurants;
    private final GetSpecializedRestaurantForType getSpecializedRestaurantForType;
    private final GetGenerallyRecommendedRestaurants getGenerallyRecommendedRestaurants;

    @PostMapping
    @HasAnyPermission({Permission.RESTAURANT_CRUD})
    public void create(@Valid @RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest) {
        registerNewRestaurant.execute(restaurantRegistrationRequest);
    }

    @GetMapping("/no-owner")
    @HasAnyPermission({Permission.RESTAURANT_CRUD})
    public List<RestaurantResponse> getRestaurantsWithoutOwner() {
        return getRestaurantsWithoutOwner.execute();
    }

    @GetMapping("/all")
    public List<RestaurantResponse> getAllRestaurants() {
        return getAllRestaurants.execute();
    }

    @GetMapping("/specializedFor")
    public List<RestaurantResponse> getSpecializedRestaurantsForType(@RequestParam MenuItemType type) {
        return getSpecializedRestaurantForType.execute(type);
    }

    @GetMapping("/general-recommendation")
    public List<RestaurantResponse> getGenerallyRecommendedRestaurants() {
        return getGenerallyRecommendedRestaurants.execute();
    }
}
