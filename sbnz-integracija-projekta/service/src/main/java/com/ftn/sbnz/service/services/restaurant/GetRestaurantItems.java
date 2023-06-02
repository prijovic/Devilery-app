package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.converter.MenuItemConverter;
import com.ftn.sbnz.service.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRestaurantItems {
    private final GetRestaurantById getRestaurantById;

    public List<MenuItemResponse> execute(final UUID id) {
        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
        final Restaurant restaurant = getRestaurantById.execute(id);
        for (MenuItem menuItem : restaurant.getItems()) {
            menuItemResponses.add(MenuItemConverter.toMenuItemResponse(menuItem));
        }
        return menuItemResponses;
    }
}
