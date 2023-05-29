package com.ftn.sbnz.service.services.role;

import com.ftn.sbnz.model.models.Role;
import com.ftn.sbnz.service.exception.RoleNotFoundException;
import com.ftn.sbnz.service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetRoleByName {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role execute(final String name) {
        return roleRepository.getByName(name).orElseThrow(RoleNotFoundException::new);
    }
}
