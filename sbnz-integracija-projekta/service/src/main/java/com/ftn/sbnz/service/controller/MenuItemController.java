package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.request.restaurant.NewMenuItemRequest;
import com.ftn.sbnz.service.services.menuItem.CreateMenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
    private final CreateMenuItem createMenuItem;

    @PostMapping
    public void createMenuItem(@RequestBody NewMenuItemRequest newMenuItemRequest) {
        createMenuItem.execute(newMenuItemRequest);
    }

    @PutMapping("/{id}")
    public void updateMenuItem(@NotBlank @PathVariable UUID id) {
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@NotBlank @PathVariable UUID id) {
    }
}
