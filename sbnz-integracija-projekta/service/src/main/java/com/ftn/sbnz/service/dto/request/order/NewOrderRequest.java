package com.ftn.sbnz.service.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class NewOrderRequest {
    @NotNull
    List<UUID> itemIds;

    @NotNull
    UUID addressId;

    @NotNull
    Double deliveryDistance;
}
