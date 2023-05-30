package com.ftn.sbnz.service.services.kie;

import lombok.RequiredArgsConstructor;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GetRestaurantKieSession {

    public KieSession execute() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("restaurantKieSession");
        kieSession.setGlobal("now", LocalDateTime.now());
        return kieSession;
    }
}
