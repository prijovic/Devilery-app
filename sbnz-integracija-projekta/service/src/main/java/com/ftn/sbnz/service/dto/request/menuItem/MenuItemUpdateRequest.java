package com.ftn.sbnz.service.dto.request.menuItem;

import com.ftn.sbnz.model.models.MenuItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class MenuItemUpdateRequest {
    @NotNull
    private String description;

    @NotNull
    private String picture;

    @NotNull
    private MenuItemType type;

    @NotNull
    private Double price;

    @NotNull
    private Boolean available;
}
