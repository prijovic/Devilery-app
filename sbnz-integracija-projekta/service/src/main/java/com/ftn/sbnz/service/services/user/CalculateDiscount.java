package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.User;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateDiscount {
    private final KieSession kieSession;

    public void execute(User user) {
        kieSession.insert(user);
        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("discount").clear();
    }
}
