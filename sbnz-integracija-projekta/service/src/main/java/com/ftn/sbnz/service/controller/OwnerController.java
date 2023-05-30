package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.owner.OwnerRegistrationRequest;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.owner.RegisterNewOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final RegisterNewOwner registerNewOwner;

    @PostMapping
    @HasAnyPermission({Permission.USER_CRUD})
    public void create(@Valid @RequestBody OwnerRegistrationRequest ownerRegistrationRequest) {
        registerNewOwner.execute(ownerRegistrationRequest);
    }
}
