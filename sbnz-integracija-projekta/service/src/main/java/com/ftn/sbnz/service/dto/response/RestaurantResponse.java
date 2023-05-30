package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.model.models.MenuItemType;
import com.ftn.sbnz.model.models.WorkingHours;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {
    UUID id;
    String name;
    String description;
    WorkingHours workingHours;
    Address address;
    Double minOrder;
    Integer minPrep;
    Integer maxPrep;
    Boolean isClosed;
    Boolean isNew;
    Double rating;
    Set<MenuItemType> specializedTypes;
}
