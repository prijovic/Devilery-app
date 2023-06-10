package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateOrderPrice {
    private final KieSession kieSession;

    public void execute(Order order) {
        kieSession.insert(order);
        kieSession.getAgenda().getAgendaGroup("price").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("price").clear();
    }
}
