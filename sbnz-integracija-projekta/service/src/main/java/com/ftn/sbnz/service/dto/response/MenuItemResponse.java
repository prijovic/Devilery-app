package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.MenuItemType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemResponse {
    UUID id;
    String name;
    String picture;
    String description;
    Double price;
    Boolean available;
    MenuItemType type;
}
