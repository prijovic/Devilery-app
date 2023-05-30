package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.dto.response.UserResponse;

public class UserConverter {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}
