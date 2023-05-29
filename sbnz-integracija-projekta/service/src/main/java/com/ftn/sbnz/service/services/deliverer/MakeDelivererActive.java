package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.service.services.user.GetUserByEmail;
import com.ftn.sbnz.service.services.user.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeDelivererActive {
    private final GetUserByEmail getUserByEmail;

    private final SaveUser saveUser;

    public Deliverer execute(String email) {
        Deliverer deliverer = (Deliverer) getUserByEmail.execute(email);
        deliverer.setActive(true);
        return (Deliverer) saveUser.execute(deliverer);
    }
}
