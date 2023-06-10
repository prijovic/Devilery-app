//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.Order;
//import com.ftn.sbnz.model.models.User;
//import org.drools.decisiontable.ExternalSpreadsheetCompiler;
//import org.junit.Test;
//import org.kie.api.builder.Message;
//import org.kie.api.builder.Results;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.utils.KieHelper;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class DiscountTest {
//
//    @Test
//    public void testCalculateUserDiscount15() {
//        User user = createUserWithOrders(10000);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(user);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(10000, user.getOrders().size());
//        assertEquals(0.15, user.getDiscount());
//    }
//
//    @Test
//    public void testCalculateUserDiscount10() {
//        User user = createUserWithOrders(1000);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(user);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(1000, user.getOrders().size());
//        assertEquals(0.1, user.getDiscount());
//    }
//
//    @Test
//    public void testCalculateUserDiscount8() {
//        User user = createUserWithOrders(500);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(user);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(500, user.getOrders().size());
//        assertEquals(0.08, user.getDiscount());
//    }
//
//    @Test
//    public void testCalculateUserDiscount5() {
//        User user = createUserWithOrders(100);
//        KieSession kieSession = initKieSession();
//
//        kieSession.insert(user);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(100, user.getOrders().size());
//        assertEquals(0.05, user.getDiscount());
//    }
//
//    private User createUserWithOrders(int ordersNumber) {
//        User user = new User();
//        List<Order> orders = new ArrayList<>();
//        for (int i = 0; i < ordersNumber; i++) {
//            Order order = new Order();
//            orders.add(order);
//        }
//        user.setOrders(orders);
//        return user;
//    }
//
//    private KieSession initKieSession() {
//        InputStream template = DiscountTest.class.getResourceAsStream("/rules/discount/discount.drt");
//        InputStream data = DiscountTest.class.getResourceAsStream("/rules/discount/discount.xls");
//
//        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
//        String drl = converter.compile(data, template, 3, 2);
//
//        return createKieSessionFromDRL(drl);
//    }
//
//    private KieSession createKieSessionFromDRL(String drl) {
//        KieHelper kieHelper = new KieHelper();
//        kieHelper.addContent(drl, ResourceType.DRL);
//
//        Results results = kieHelper.verify();
//
//        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
//            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
//            for (Message message : messages) {
//                System.out.println("Error: " + message.getText());
//            }
//
//            throw new IllegalStateException("Compilation errors were found. Check the logs.");
//        }
//
//        return kieHelper.build().newKieSession();
//    }
//}
