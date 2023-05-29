package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.request.auth.LoginRequest;
import com.ftn.sbnz.service.dto.response.AuthTokenResponse;
import com.ftn.sbnz.service.services.auth.LogInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LogInUser loginUser;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.status(200).body(loginUser.execute(loginRequest.getEmail(), loginRequest.getPassword()));
    }

}
