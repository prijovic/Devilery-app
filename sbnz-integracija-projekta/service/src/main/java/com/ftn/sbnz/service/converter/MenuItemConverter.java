package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.service.dto.response.MenuItemResponse;

public class MenuItemConverter {

    public static MenuItemResponse toMenuItemResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .picture(menuItem.getPicture())
                .price(menuItem.getPrice())
                .available(menuItem.isAvailable())
                .type(menuItem.getType())
                .build();
    }
}
