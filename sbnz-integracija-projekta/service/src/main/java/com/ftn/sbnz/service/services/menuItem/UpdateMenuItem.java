package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.service.converter.MenuItemConverter;
import com.ftn.sbnz.service.dto.request.menuItem.MenuItemUpdateRequest;
import com.ftn.sbnz.service.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateMenuItem {
    private final GetMenuItemById getMenuItemById;
    private final SaveMenuItem saveMenuItem;

    public MenuItemResponse execute(UUID id, MenuItemUpdateRequest menuItemUpdateRequest) {
        MenuItem menuItem = getMenuItemById.execute(id);

        menuItem.setPrice(menuItemUpdateRequest.getPrice());
        menuItem.setDescription(menuItemUpdateRequest.getDescription());
        menuItem.setPicture(menuItemUpdateRequest.getPicture());
        menuItem.setType(menuItemUpdateRequest.getType());
        menuItem.setAllergens(menuItemUpdateRequest.getAllergens());
        menuItem.setAvailable(menuItemUpdateRequest.getAvailable());

        return MenuItemConverter.toMenuItemResponse(saveMenuItem.execute(menuItem));
    }
}
