package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.User;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountTest {
    @Test
    public void testCalculateUserDiscount15() {
        User user = new User();
        user.setDiscount((double) 0);
        List<Order> orders = new ArrayList<>();
        // Add orders with a size of 10,000 or more
        for (int i = 0; i < 10000; i++) {
            Order order = new Order();
            orders.add(order);
        }
        user.setOrders(orders);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("discountKieSession");

        kieSession.insert(user);
        kieSession.getAgenda().getAgendaGroup("Discount Rules").setFocus();
        kieSession.fireAllRules();

        assertEquals(0.15, user.getDiscount(), 0.01);
    }

    @Test
    public void testCalculateUserDiscount10() {
        User user = new User();
        user.setDiscount((double) 0);
        List<Order> orders = new ArrayList<>();
        // Add orders with a size of 10,000 or more
        for (int i = 0; i < 1000; i++) {
            Order order = new Order();
            orders.add(order);
        }
        user.setOrders(orders);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("discountKieSession");

        kieSession.insert(user);
        kieSession.getAgenda().getAgendaGroup("Discount Rules").setFocus();
        kieSession.fireAllRules();

        assertEquals(0.1, user.getDiscount(), 0.01);
    }

    @Test
    public void testCalculateUserDiscount8() {
        User user = new User();
        user.setDiscount((double) 0);
        List<Order> orders = new ArrayList<>();
        // Add orders with a size of 10,000 or more
        for (int i = 0; i < 500; i++) {
            Order order = new Order();
            orders.add(order);
        }
        user.setOrders(orders);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("discountKieSession");

        kieSession.insert(user);
        kieSession.getAgenda().getAgendaGroup("Discount Rules").setFocus();
        kieSession.fireAllRules();

        assertEquals(0.08, user.getDiscount(), 0.001);
    }

    @Test
    public void testCalculateUserDiscount5() {
        User user = new User();
        user.setDiscount((double) 0);
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            orders.add(order);
        }
        user.setOrders(orders);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("discountKieSession");

        kieSession.insert(user);
        kieSession.getAgenda().getAgendaGroup("Discount Rules").setFocus();
        kieSession.fireAllRules();

        assertEquals(0.05, user.getDiscount(), 0.001);
    }

}
