package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.services.kie.GetRestaurantKieSession;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunRestaurantRules {
    private final GetRestaurantKieSession getRestaurantKieSession;

    public void execute(Restaurant restaurant) {
        KieSession kieSession = getRestaurantKieSession.execute();
        kieSession.insert(restaurant);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
