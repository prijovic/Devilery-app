package com.ftn.sbnz.service.services.auth;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.dto.response.AuthTokenResponse;
import com.ftn.sbnz.service.exception.UserBlockedException;
import com.ftn.sbnz.service.exception.UserNotActiveException;
import com.ftn.sbnz.service.services.deliverer.MakeDelivererActive;
import com.ftn.sbnz.service.services.jwt.JwtGenerateToken;
import com.ftn.sbnz.service.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInUser {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerateToken jwtGenerateToken;
    private final GetUserByEmail getUserByEmail;
    private final CustomProperties customProperties;
    private final MakeDelivererActive makeDelivererActive;


    public AuthTokenResponse execute(final String email, final String password) {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (final Exception e) {
            throw new BadCredentialsException("Bad login credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final User user = getUserByEmail.execute(userDetails.getUsername());

        if (user.isBlocked()) {
            throw new UserBlockedException();
        }

        if (!user.isActive()) {
            throw new UserNotActiveException();
        }

        if (user.getRole().getName().equals("DRIVER")) {
            makeDelivererActive.execute(user.getEmail());
        }

        return new AuthTokenResponse(jwtGenerateToken.execute(user.getEmail(), customProperties.getAuthTokenExpirationMilliseconds(), user.getRole()),
                customProperties.getAuthTokenExpirationMilliseconds(), user.getRole().getName());
    }
}
