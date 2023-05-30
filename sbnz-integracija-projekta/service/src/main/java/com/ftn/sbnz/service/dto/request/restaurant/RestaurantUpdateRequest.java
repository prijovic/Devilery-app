package com.ftn.sbnz.service.dto.request.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantUpdateRequest {
    @NotNull
    private String description;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;

    @NotNull
    private Integer minPreparation;

    @NotNull
    private Integer maxPreparation;

    @NotNull
    private Double minOrder;

    @NotNull
    private Boolean isClosed;
}
