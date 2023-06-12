package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.service.repository.DelivererRepository;
import com.ftn.sbnz.service.services.charge.SaveCharge;
import com.ftn.sbnz.service.services.user.SaveUser;
import com.ftn.sbnz.service.services.websocket.OrderAssignedToDeliverer;
import lombok.RequiredArgsConstructor;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignDelivererToOrder {
    private final KieSession kieSession;
    private final DelivererRepository delivererRepository;
    private final OrderAssignedToDeliverer orderAssignedToDeliverer;
    private final SaveOrder saveOrder;
    private final SaveUser saveDeliverer;
    private final SaveCharge saveCharge;

    public void execute(Order order) {
        kieSession.insert(order);
        for (Deliverer deliverer : delivererRepository.findAll()) {
            kieSession.insert(deliverer);
        }
        kieSession.getAgenda().getAgendaGroup("delivery").setFocus();
        kieSession.fireAllRules();
        List<Object> objects = new ArrayList<>(kieSession.getObjects(new ClassObjectFilter(Object.class)));
        for (Object object : objects) {
            kieSession.delete(kieSession.getFactHandle(object));
        }
        if (order.getDeliverer() != null) {
            order.setCharge(saveCharge.execute(order.getCharge()));
            order = saveOrder.execute(order);
            Deliverer deliverer = order.getDeliverer();
            deliverer.getOrdersDelivered().add(order);
            deliverer = (Deliverer) saveDeliverer.execute(deliverer);
            orderAssignedToDeliverer.execute(deliverer.getEmail(), order.getId());
        }
    }
}
