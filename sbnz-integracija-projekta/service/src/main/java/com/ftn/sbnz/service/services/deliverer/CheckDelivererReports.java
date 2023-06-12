package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Report;
import com.ftn.sbnz.service.services.websocket.UserBlocked;
import lombok.RequiredArgsConstructor;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckDelivererReports {
    private final KieSession kieSession;
    private final UserBlocked userBlocked;

    public Deliverer execute(Report report) {
        Deliverer deliverer = report.getDeliverer();
        for (Report rep : deliverer.getReports()) {
            kieSession.insert(rep);
        }
        kieSession.insert(deliverer);
        kieSession.insert(report);
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();
        kieSession.fireAllRules();

        List<Object> objects = new ArrayList<>(kieSession.getObjects(new ClassObjectFilter(Object.class)));
        for (Object object : objects) {
            kieSession.delete(kieSession.getFactHandle(object));
        }

        if (deliverer.isBlocked()) {
            userBlocked.execute();
        }

        return deliverer;
    }
}
