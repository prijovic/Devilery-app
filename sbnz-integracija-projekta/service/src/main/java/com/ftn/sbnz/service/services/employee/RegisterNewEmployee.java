package com.ftn.sbnz.service.services.employee;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.RestaurantEmployee;
import com.ftn.sbnz.model.models.Role;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.configProperties.CustomProperties;
import com.ftn.sbnz.service.dto.request.employee.EmployeeRegistrationRequest;
import com.ftn.sbnz.service.exception.UserAlreadyExistsException;
import com.ftn.sbnz.service.services.jwt.JwtGenerateToken;
import com.ftn.sbnz.service.services.mail.SendMail;
import com.ftn.sbnz.service.services.model.EmailDetails;
import com.ftn.sbnz.service.services.restaurant.GetRestaurantById;
import com.ftn.sbnz.service.services.restaurant.SaveRestaurant;
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
public class RegisterNewEmployee {
    private final UserExistsByEmail userExistsByEmail;
    private final PasswordEncoder passwordEncoder;
    private final SaveEmployee saveEmployee;
    private final SaveRestaurant saveRestaurant;
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;
    private final GetRoleByName getRoleByName;
    private final GetRestaurantById getRestaurantById;

    public User execute(EmployeeRegistrationRequest employeeRegistrationRequest) {
        if (userExistsByEmail.execute(employeeRegistrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        Restaurant restaurant = getRestaurantById.execute(employeeRegistrationRequest.getRestaurantId());

        RestaurantEmployee employee = RestaurantEmployee.builder()
                .email(employeeRegistrationRequest.getEmail())
                .name(employeeRegistrationRequest.getName())
                .surname(employeeRegistrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(employeeRegistrationRequest.getPassword()))
                .phoneNumber(employeeRegistrationRequest.getPhoneNumber())
                .profilePicture(employeeRegistrationRequest.getProfilePicture())
                .role(getRoleByName.execute("EMPLOYEE"))
                .blocked(false)
                .active(false)
                .restaurant(restaurant)
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(employee.getEmail(), employee.getRole());
        final EmailDetails emailDetails = new EmailDetails(employee.getEmail(), toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        employee = saveEmployee.execute(employee);
        restaurant.getEmployees().add(employee);
        saveRestaurant.execute(restaurant);

        return employee;
    }

    private String constructActivateEmailUrl(final String passengerEmail, final Role role) {
        final String authToken = jwtGenerateToken.execute(passengerEmail, customProperties.getJwtActivateEmailTokenExpiration(), role);
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }

}
