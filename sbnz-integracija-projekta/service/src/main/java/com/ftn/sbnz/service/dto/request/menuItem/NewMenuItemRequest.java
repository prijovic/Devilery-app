package com.ftn.sbnz.service.dto.request.menuItem;

import com.ftn.sbnz.model.models.MenuItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class NewMenuItemRequest {
    @NotNull
    private UUID restaurantId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String picture;

    @NotNull
    private MenuItemType type;

    @NotNull
    private Double price;
}
