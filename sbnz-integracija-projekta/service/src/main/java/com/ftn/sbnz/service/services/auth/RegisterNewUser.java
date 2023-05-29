package com.ftn.sbnz.service.services.auth;

import com.ftn.sbnz.model.models.Role;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.dto.request.auth.RegistrationRequest;
import com.ftn.sbnz.service.exception.UserAlreadyExistsException;
import com.ftn.sbnz.service.services.jwt.JwtGenerateToken;
import com.ftn.sbnz.service.services.mail.SendMail;
import com.ftn.sbnz.service.services.model.EmailDetails;
import com.ftn.sbnz.service.services.user.SaveUser;
import com.ftn.sbnz.service.services.user.UserExistsByEmail;
import com.ftn.sbnz.service.translations.Codes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.ftn.sbnz.service.constants.LinkConstants.EMAIL_ACTIVATION_PATH;
import static com.ftn.sbnz.service.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class RegisterNewUser {
    private final UserExistsByEmail userExistsByEmail;
    private final PasswordEncoder passwordEncoder;
    private final SaveUser saveUser;
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;

    public User execute(@Valid final RegistrationRequest registrationRequest, Role role) {
        if (userExistsByEmail.execute(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final User user = User.builder()
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .profilePicture(registrationRequest.getProfilePicture())
                .role(role)
                .blocked(false)
                .active(false)
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(user.getEmail());
        final EmailDetails emailDetails = new EmailDetails(user.getEmail(), toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return saveUser.execute(user);
    }

    private String constructActivateEmailUrl(final String passengerEmail) {
        final String authToken = jwtGenerateToken.execute(passengerEmail, customProperties.getJwtActivateEmailTokenExpiration());
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
