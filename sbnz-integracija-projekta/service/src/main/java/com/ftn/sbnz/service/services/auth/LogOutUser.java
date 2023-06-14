package com.ftn.sbnz.service.services.auth;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.deliverer.ChangeDelivererOnlineStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogOutUser {
    private final GetLoggedInUser getLoggedInUser;
    private final ChangeDelivererOnlineStatus changeDelivererOnlineStatus;

    public User execute() {
        User user = getLoggedInUser.execute();
        if (user instanceof Deliverer) {
            changeDelivererOnlineStatus.execute(user.getId(), false);
        }
        return user;
    }
}
