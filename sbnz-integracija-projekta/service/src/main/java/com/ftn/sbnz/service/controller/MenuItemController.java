package com.ftn.sbnz.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/menu-item")
@RequiredArgsConstructor
public class MenuItemController {

    @PutMapping("/{id}")
    public void updateMenuItem(@NotBlank @PathVariable UUID id) {
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@NotBlank @PathVariable UUID id) {
    }
}
