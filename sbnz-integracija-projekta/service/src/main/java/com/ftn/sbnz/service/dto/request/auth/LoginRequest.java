package com.ftn.sbnz.service.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
