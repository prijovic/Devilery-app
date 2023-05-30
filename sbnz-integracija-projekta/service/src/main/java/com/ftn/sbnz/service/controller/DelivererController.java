package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.deliverer.DelivererRegistrationRequest;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.deliverer.RegisterNewDeliverer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/deliverer")
@RequiredArgsConstructor
public class DelivererController {
    private final RegisterNewDeliverer registerNewDeliverer;

    @PostMapping
    @HasAnyPermission({Permission.USER_CRUD})
    public void create(@Valid @RequestBody DelivererRegistrationRequest delivererRegistrationRequest) {
        registerNewDeliverer.execute(delivererRegistrationRequest);
    }
}
