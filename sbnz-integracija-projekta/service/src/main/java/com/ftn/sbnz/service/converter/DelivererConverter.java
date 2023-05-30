package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.service.dto.response.OrderDelivererResponse;

public class DelivererConverter {

    public static OrderDelivererResponse toOrderDelivererResponse(Deliverer deliverer) {
        return OrderDelivererResponse.builder()
                .id(deliverer.getId())
                .name(deliverer.getName())
                .surname(deliverer.getSurname())
                .email(deliverer.getEmail())
                .phoneNumber(deliverer.getPhoneNumber())
                .profilePicture(deliverer.getProfilePicture())
                .rating(deliverer.getRating())
                .type(deliverer.getType())
                .build();
    }
}
