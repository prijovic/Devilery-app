package com.ftn.sbnz.service.services.auth;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.exception.UserNotFoundException;
import com.ftn.sbnz.service.services.jwt.JwtValidateAndGetUsername;
import com.ftn.sbnz.service.services.user.GetUserByEmail;
import com.ftn.sbnz.service.services.user.SaveUser;
import com.ftn.sbnz.service.services.user.UserExistsByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivateEmail {
    private final JwtValidateAndGetUsername jwtValidateAndGetUsername;
    private final GetUserByEmail getUserByEmail;
    private final UserExistsByEmail userExistsByEmail;
    private final SaveUser saveUser;

    public User execute(String token) {
        final String email = jwtValidateAndGetUsername.execute(token);
        if (!userExistsByEmail.execute(email)) {
            throw new UserNotFoundException();
        }
        final User user = getUserByEmail.execute(email);
        user.setActive(true);
        return saveUser.execute(user);
    }
}