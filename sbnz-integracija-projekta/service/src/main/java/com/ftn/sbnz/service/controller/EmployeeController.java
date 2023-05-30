package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Permission;
import com.ftn.sbnz.service.dto.request.employee.EmployeeRegistrationRequest;
import com.ftn.sbnz.service.security.HasAnyPermission;
import com.ftn.sbnz.service.services.employee.RegisterNewEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final RegisterNewEmployee registerNewEmployee;

    @PostMapping
    @HasAnyPermission({Permission.CREATE_EMPLOYEE})
    public void create(@Valid @RequestBody EmployeeRegistrationRequest employeeRegistrationRequest) {
        registerNewEmployee.execute(employeeRegistrationRequest);
    }
}
