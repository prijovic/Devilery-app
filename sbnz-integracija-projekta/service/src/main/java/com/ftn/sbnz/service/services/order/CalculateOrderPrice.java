package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.services.kie.GetPriceKieSession;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateOrderPrice {
    private final GetPriceKieSession getPriceKieSession;

    public void execute(Order order) {
        KieSession kieSession = getPriceKieSession.execute();

        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
