package com.ftn.sbnz.service.services.auth;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.exception.UnauthorizedException;
import com.ftn.sbnz.service.services.user.GetUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class GetLoggedInUser {
    private final GetUserById getUserById;

    @Transactional(readOnly = true)
    public User execute() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException();
        }

        final UUID userId = ((User) authentication.getPrincipal()).getId();

        return getUserById.execute(userId);
    }
}
