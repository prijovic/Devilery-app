package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.Role;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.exception.UserAlreadyExistsException;
import com.ftn.sbnz.service.services.jwt.JwtGenerateToken;
import com.ftn.sbnz.service.services.mail.SendMail;
import com.ftn.sbnz.service.services.model.EmailDetails;
import com.ftn.sbnz.service.translations.Codes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ftn.sbnz.service.constants.LinkConstants.EMAIL_ACTIVATION_PATH;
import static com.ftn.sbnz.service.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class RegisterNewUser {
    private final UserExistsByEmail userExistsByEmail;
    private final SaveUser saveUser;
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;

    public User execute(final User user) {
        if (userExistsByEmail.execute(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final String activateEmailUrl = constructActivateEmailUrl(user.getEmail(), user.getRole());
        final EmailDetails emailDetails = new EmailDetails(user.getEmail(), toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return saveUser.execute(user);
    }

    private String constructActivateEmailUrl(final String userEmail, final Role role) {
        final String authToken = jwtGenerateToken.execute(userEmail, customProperties.getJwtActivateEmailTokenExpiration(), role);
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
