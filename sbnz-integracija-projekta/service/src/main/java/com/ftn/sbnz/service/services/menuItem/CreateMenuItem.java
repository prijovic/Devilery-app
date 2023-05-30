package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.dto.request.restaurant.NewMenuItemRequest;
import com.ftn.sbnz.service.services.restaurant.GetRestaurantById;
import com.ftn.sbnz.service.services.restaurant.SaveRestaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateMenuItem {
    private final GetRestaurantById getRestaurantById;
    private final SaveMenuItem saveMenuItem;
    private final SaveRestaurant saveRestaurant;

    public MenuItem execute(NewMenuItemRequest newMenuItemRequest) {
        Restaurant restaurant = getRestaurantById.execute(newMenuItemRequest.getRestaurantId());

        MenuItem menuItem = MenuItem.builder()
                .type(newMenuItemRequest.getType())
                .name(newMenuItemRequest.getName())
                .description(newMenuItemRequest.getDescription())
                .allergens(newMenuItemRequest.getAllergens())
                .picture(newMenuItemRequest.getPicture())
                .price(newMenuItemRequest.getPrice())
                .createdOn(LocalDateTime.now())
                .available(true)
                .restaurant(restaurant)
                .build();

        menuItem = saveMenuItem.execute(menuItem);

        restaurant.getItems().add(menuItem);
        saveRestaurant.execute(restaurant);

        return menuItem;
    }
}
