package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.DelivererStatus;
import com.ftn.sbnz.model.models.DelivererType;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.dto.request.deliverer.DelivererRegistrationRequest;
import com.ftn.sbnz.service.services.role.GetRoleByName;
import com.ftn.sbnz.service.services.user.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class RegisterNewDeliverer {
    private final RegisterUser registerUser;
    private final PasswordEncoder passwordEncoder;
    private final GetRoleByName getRoleByName;

    public User execute(@Valid final DelivererRegistrationRequest delivererRegistrationRequest) {
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

        return registerUser.execute(deliverer);
    }

}
