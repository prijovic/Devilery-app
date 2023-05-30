package com.ftn.sbnz.service.dto.request.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantRegistrationRequest {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;

    @NotNull
    private String addressName;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @NotNull
    private Integer minPreparation;

    @NotNull
    private Integer maxPreparation;

    @NotNull
    private Double minOrder;

    private UUID ownerId;
}
