package com.ftn.sbnz.service.services.owner;

import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.dto.request.owner.OwnerRegistrationRequest;
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

import static com.ftn.sbnz.service.constants.LinkConstants.EMAIL_ACTIVATION_PATH;
import static com.ftn.sbnz.service.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class RegisterNewOwner {
    private final UserExistsByEmail userExistsByEmail;
    private final PasswordEncoder passwordEncoder;
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;
    private final GetRoleByName getRoleByName;
    private final AddRestaurantsToOwner addRestaurantsToOwner;
    private final SaveOwner saveOwner;

    public User execute(OwnerRegistrationRequest ownerRegistrationRequest) {
        if (userExistsByEmail.execute(ownerRegistrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final RestaurantOwner owner = RestaurantOwner.builder()
                .email(ownerRegistrationRequest.getEmail())
                .name(ownerRegistrationRequest.getName())
                .surname(ownerRegistrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(ownerRegistrationRequest.getPassword()))
                .phoneNumber(ownerRegistrationRequest.getPhoneNumber())
                .profilePicture(ownerRegistrationRequest.getProfilePicture())
                .role(getRoleByName.execute("OWNER"))
                .blocked(false)
                .active(false)
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(owner.getEmail());
        final EmailDetails emailDetails = new EmailDetails(owner.getEmail(), toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return addRestaurantsToOwner.execute(saveOwner.execute(owner), ownerRegistrationRequest.getRestaurants());
    }

    private String constructActivateEmailUrl(final String passengerEmail) {
        final String authToken = jwtGenerateToken.execute(passengerEmail, customProperties.getJwtActivateEmailTokenExpiration());
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
