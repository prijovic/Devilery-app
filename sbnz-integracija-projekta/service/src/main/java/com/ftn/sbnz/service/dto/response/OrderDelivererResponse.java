package com.ftn.sbnz.service.dto.response;

import com.ftn.sbnz.model.models.DelivererType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDelivererResponse extends UserResponse {
    DelivererType type;
    Double rating;
}
