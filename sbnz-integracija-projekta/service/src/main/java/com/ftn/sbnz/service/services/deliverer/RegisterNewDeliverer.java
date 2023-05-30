package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.dto.request.deliverer.DelivererRegistrationRequest;
import com.ftn.sbnz.service.exception.UserAlreadyExistsException;
import com.ftn.sbnz.service.services.jwt.JwtGenerateToken;
import com.ftn.sbnz.service.services.mail.SendMail;
import com.ftn.sbnz.service.services.model.EmailDetails;
import com.ftn.sbnz.service.services.role.GetRoleByName;
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
public class RegisterNewDeliverer {
    private final UserExistsByEmail userExistsByEmail;
    private final PasswordEncoder passwordEncoder;
    private final SaveDeliverer saveDeliverer;
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;
    private final GetRoleByName getRoleByName;

    public User execute(@Valid final DelivererRegistrationRequest delivererRegistrationRequest) {
        if (userExistsByEmail.execute(delivererRegistrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final Deliverer deliverer = Deliverer.builder()
                .email(delivererRegistrationRequest.getEmail())
                .name(delivererRegistrationRequest.getName())
                .surname(delivererRegistrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(delivererRegistrationRequest.getPassword()))
                .phoneNumber(delivererRegistrationRequest.getPhoneNumber())
                .profilePicture(delivererRegistrationRequest.getProfilePicture())
                .role(getRoleByName.execute("DELIVERER"))
                .type(DelivererType.valueOf(delivererRegistrationRequest.getType()))
                .blocked(false)
                .active(false)
                .status(DelivererStatus.UNAVAILABLE)
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(deliverer.getEmail(), deliverer.getRole());
        final EmailDetails emailDetails = new EmailDetails(deliverer.getEmail(), toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return saveDeliverer.execute(deliverer);
    }

    private String constructActivateEmailUrl(final String passengerEmail, final Role role) {
        final String authToken = jwtGenerateToken.execute(passengerEmail, customProperties.getJwtActivateEmailTokenExpiration(), role);
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
