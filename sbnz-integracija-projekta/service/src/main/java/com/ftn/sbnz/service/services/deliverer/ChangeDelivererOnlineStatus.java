package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.service.services.user.GetUserById;
import com.ftn.sbnz.service.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeDelivererOnlineStatus {
    private final GetUserById getUserById;
    private final SaveUser saveUser;

    public Deliverer execute(UUID id, boolean isOnline) {
        Deliverer deliverer = (Deliverer) getUserById.execute(id);
        deliverer.setOnline(isOnline);
        return (Deliverer) saveUser.execute(deliverer);
    }
}
