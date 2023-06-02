package com.ftn.sbnz.service.services.owner;

import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.dto.request.owner.OwnerRegistrationRequest;
import com.ftn.sbnz.service.services.role.GetRoleByName;
import com.ftn.sbnz.service.services.user.RegisterNewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterNewOwner {
    private final PasswordEncoder passwordEncoder;
    private final RegisterNewUser registerNewUser;
    private final GetRoleByName getRoleByName;
    private final AddRestaurantsToOwner addRestaurantsToOwner;

    public User execute(OwnerRegistrationRequest ownerRegistrationRequest) {
        RestaurantOwner owner = RestaurantOwner.builder()
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
        owner = (RestaurantOwner) registerNewUser.execute(owner);
        return addRestaurantsToOwner.execute(owner, ownerRegistrationRequest.getRestaurants());
    }
}
