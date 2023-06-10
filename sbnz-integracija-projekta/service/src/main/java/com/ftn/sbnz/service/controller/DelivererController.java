package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.deliverer.DelivererRegistrationRequest;
import com.ftn.sbnz.service.repository.DelivererRepository;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.deliverer.RegisterNewDeliverer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliverer")
@RequiredArgsConstructor
public class DelivererController {
    private final RegisterNewDeliverer registerNewDeliverer;
    private final DelivererRepository delivererRepository;

    @GetMapping("/all")
    public List<Deliverer> test() {
        return delivererRepository.findAll();
    }

    @PostMapping
    @HasAnyPermission({Permission.USER_CRUD})
    public void create(@Valid @RequestBody DelivererRegistrationRequest delivererRegistrationRequest) {
        registerNewDeliverer.execute(delivererRegistrationRequest);
    }
}
