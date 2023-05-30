package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.kie.GetDiscountKieSession;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateDiscount {
    private final GetDiscountKieSession getDiscountKieSession;

    public void execute(User user) {
        KieSession kieSession = getDiscountKieSession.execute();

        kieSession.insert(user);
        kieSession.fireAllRules();

        kieSession.dispose();
    }
}
