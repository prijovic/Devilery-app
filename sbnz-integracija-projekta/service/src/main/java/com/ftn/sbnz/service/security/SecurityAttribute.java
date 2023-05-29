package com.ftn.sbnz.service.security;

import com.ftn.sbnz.model.models.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityAttribute implements ConfigAttribute {
    private final List<Permission> permissions;

    @Override
    public String getAttribute() {
        return permissions.stream().map(Enum::name).collect(Collectors.joining(","));
    }
}
