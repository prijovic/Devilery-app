//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.MenuItem;
//import com.ftn.sbnz.model.models.Order;
//import com.ftn.sbnz.model.models.User;
//import org.junit.Test;
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PriceTest {
//
//    @Test
//    public void testOrderPriceCalculationWithDiscount() {
//        double deliveryDistance = 2.0;
//        Double totalItemPrices = 0.0;
//        double discount = 0.1;
//
//        User user = new User();
//        Order order = new Order();
//        order.setDeliveryDistance(deliveryDistance);
//        MenuItem item1 = new MenuItem();
//        item1.setPrice(10.0);
//        MenuItem item2 = new MenuItem();
//        item2.setPrice(5.0);
//        order.getItems().add(item1);
//        order.getItems().add(item2);
//        user.setDiscount(discount);
//        order.setCustomer(user);
//
//        for (MenuItem item : order.getItems()) {
//            totalItemPrices += item.getPrice();
//        }
//        Double price = (totalItemPrices + deliveryDistance * 1.1) * (1 - discount);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(order);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(price, order.getCharge().getTotal());
//    }
//
//    @Test
//    public void testOrderPriceCalculationWithoutDiscount() {
//        double deliveryDistance = 2.0;
//        Double totalItemPrices = 0.0;
//
//        User user = new User();
//        Order order = new Order();
//        order.setDeliveryDistance(deliveryDistance);
//        MenuItem item1 = new MenuItem();
//        item1.setPrice(10.0);
//        MenuItem item2 = new MenuItem();
//        item2.setPrice(5.0);
//        order.getItems().add(item1);
//        order.getItems().add(item2);
//        order.setCustomer(user);
//
//        for (MenuItem item : order.getItems()) {
//            totalItemPrices += item.getPrice();
//        }
//        Double price = (totalItemPrices + deliveryDistance * 1.1);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(order);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(price, order.getCharge().getTotal());
//    }
//
//    private KieSession initKieSession() {
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession kieSession = kContainer.newKieSession("priceKieSession");
//        return kieSession;
//    }
//}
