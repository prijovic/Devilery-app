package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunRestaurantRules {
    private final KieSession kieSession;

    public void execute(Restaurant restaurant) {
        kieSession.insert(restaurant);
        kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("restaurant").clear();
    }
}
