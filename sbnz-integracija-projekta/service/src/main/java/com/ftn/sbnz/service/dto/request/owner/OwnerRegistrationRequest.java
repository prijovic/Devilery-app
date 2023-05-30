package com.ftn.sbnz.service.dto.request.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OwnerRegistrationRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String profilePicture;

    @NotEmpty
    private List<UUID> restaurants;
}
