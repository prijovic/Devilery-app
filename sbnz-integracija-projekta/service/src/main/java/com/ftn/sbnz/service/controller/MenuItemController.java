package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.request.menuItem.MenuItemUpdateRequest;
import com.ftn.sbnz.service.dto.request.menuItem.NewMenuItemRequest;
import com.ftn.sbnz.service.services.menuItem.CreateMenuItem;
import com.ftn.sbnz.service.services.menuItem.UpdateMenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
    private final CreateMenuItem createMenuItem;
    private final UpdateMenuItem updateMenuItem;

    @PostMapping
    public void createMenuItem(@RequestBody NewMenuItemRequest newMenuItemRequest) {
        createMenuItem.execute(newMenuItemRequest);
    }

    @PutMapping("/{id}")
    public void updateMenuItem(@NotBlank @PathVariable UUID id, @RequestBody MenuItemUpdateRequest menuItemUpdateRequest) {
        updateMenuItem.execute(id, menuItemUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@NotBlank @PathVariable UUID id) {
    }
}
