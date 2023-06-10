package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.RestaurantEmployee;
import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.exception.UnauthorizedException;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRestaurantIdForSubscription {
    private final GetLoggedInUser getLoggedInUser;

    public UUID execute() {
        User user = getLoggedInUser.execute();
        if (user instanceof RestaurantOwner) {
            return ((RestaurantOwner) user).getRestaurants().get(0).getId();
        }
        if (user instanceof RestaurantEmployee) {
            return ((RestaurantEmployee) user).getRestaurant().getId();
        }
        throw new UnauthorizedException();
    }
}
