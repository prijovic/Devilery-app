//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.*;
//import org.drools.decisiontable.ExternalSpreadsheetCompiler;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.kie.api.builder.Message;
//import org.kie.api.builder.Results;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.utils.KieHelper;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DeliveryTest {
//
//    private static Stream<Arguments> delivererDataProvider() {
//        return Stream.of(
//                Arguments.of(DelivererType.BICYCLE, 2.1, DelivererStatus.UNAVAILABLE),
//                Arguments.of(DelivererType.BICYCLE, 1.9, DelivererStatus.DELIVERING),
//                Arguments.of(DelivererType.MOTORCYCLE, 3.1, DelivererStatus.UNAVAILABLE),
//                Arguments.of(DelivererType.MOTORCYCLE, 2.9, DelivererStatus.DELIVERING),
//                Arguments.of(DelivererType.CAR, 5.1, DelivererStatus.UNAVAILABLE),
//                Arguments.of(DelivererType.CAR, 4.9, DelivererStatus.DELIVERING)
//        );
//    }
//
//    @DisplayName("Test Deliverer Status")
//    @ParameterizedTest(name = "Deliverer Type: {0}, Delivery Distance: {1}, ExpectedStatus: {2}")
//    @MethodSource("delivererDataProvider")
//    public void testDelivererStatus(DelivererType type, double deliveryDistance, DelivererStatus expectedStatus) throws IOException {
//        Deliverer deliverer = getDelivererWithCurrentOrder(type, deliveryDistance);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(expectedStatus, deliverer.getStatus());
//    }
//
//    @Test
//    public void testWellRatedDelivererRule() throws IOException {
//        Deliverer deliverer = getRatedDeliverer(4.5, 5);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(deliverer.isWellRated());
//        assertFalse(deliverer.isBadRated());
//    }
//
//    @Test
//    public void testBadlyRatedDelivererRule() throws IOException {
//        Deliverer deliverer = getRatedDeliverer(1, 2.49);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertFalse(deliverer.isWellRated());
//        assertTrue(deliverer.isBadRated());
//    }
//
//    @Test
//    @DisplayName("Test Assign Order to Available not Bad Rated Deliverer Rule")
//    public void testAssignOrderToAvailableNotBadRatedDelivererRule() throws IOException {
//        Deliverer deliverer = getRatedDeliverer(2.5, 5);
//        deliverer.setId(UUID.randomUUID());
//        deliverer.setStatus(DelivererStatus.AVAILABLE);
//
//        Deliverer badRatedDeliverer = getRatedDeliverer(1, 2.4);
//        badRatedDeliverer.setId(UUID.randomUUID());
//        badRatedDeliverer.setStatus(DelivererStatus.AVAILABLE);
//
//        Order order = new Order();
//        order.setStatus(OrderStatus.DONE);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.insert(badRatedDeliverer);
//        kieSession.insert(order);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertFalse(deliverer.isBadRated());
//        assertTrue(badRatedDeliverer.isBadRated());
//        assertEquals(deliverer, order.getDeliverer());
//    }
//
//    @Test
//    @DisplayName("Test Assign Order to Delivering not Bad Rated Deliverer Rule")
//    public void testAssignOrderToDeliveringNotBadRatedDelivererRule() throws IOException {
//        Deliverer deliverer = getRatedDeliverer(2.5, 5);
//        deliverer.setId(UUID.randomUUID());
//        deliverer.setStatus(DelivererStatus.DELIVERING);
//
//        Deliverer badRatedDeliverer = getRatedDeliverer(1, 2.4);
//        badRatedDeliverer.setId(UUID.randomUUID());
//        badRatedDeliverer.setStatus(DelivererStatus.AVAILABLE);
//
//        Order order = new Order();
//        order.setStatus(OrderStatus.DONE);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.insert(badRatedDeliverer);
//        kieSession.insert(order);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertFalse(deliverer.isBadRated());
//        assertTrue(badRatedDeliverer.isBadRated());
//        assertEquals(deliverer, order.getDeliverer());
//    }
//
//    @Test
//    @DisplayName("Test Assign Order to Any Deliverer Rule")
//    public void testAssignOrderToAnyDelivererRule() throws IOException {
//        Deliverer deliverer = getRatedDeliverer(1, 2.4);
//        deliverer.setId(UUID.randomUUID());
//        deliverer.setStatus(DelivererStatus.AVAILABLE);
//
//        Deliverer busyDeliverer = getDelivererWithCurrentOrder(DelivererType.BICYCLE, 2.5);
//        busyDeliverer.setId(UUID.randomUUID());
//        busyDeliverer.setStatus(DelivererStatus.DELIVERING);
//
//        Order order = new Order();
//        order.setStatus(OrderStatus.DONE);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        kieSession.insert(busyDeliverer);
//        kieSession.insert(order);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertEquals(DelivererStatus.UNAVAILABLE, busyDeliverer.getStatus());
//        assertTrue(deliverer.isBadRated());
//        assertEquals(deliverer, order.getDeliverer());
//    }
//
//    private Deliverer getRatedDeliverer(double minRate, double maxRate) {
//        Deliverer deliverer = new Deliverer();
//        deliverer.setReviews(getRatedReviews(minRate, maxRate));
//        return deliverer;
//    }
//
//    private Deliverer getDelivererWithCurrentOrder(DelivererType type, double deliveryDistance) {
//        Deliverer deliverer = new Deliverer();
//        deliverer.setType(type);
//        deliverer.setStatus(DelivererStatus.DELIVERING);
//
//        Order order = new Order();
//        order.setDeliveryDistance(deliveryDistance);
//        order.setStatus(OrderStatus.BEING_DELIVERED);
//
//        deliverer.getOrdersDelivered().add(order);
//        return deliverer;
//    }
//
//    protected List<Review> getRatedReviews(double minRate, double maxRate) {
//        List<Review> reviews = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            Review review = new Review();
//            double randomNumber = minRate + random.nextDouble() * (maxRate - minRate);
//            review.setDelivererRating(randomNumber);
//            reviews.add(review);
//        }
//        return reviews;
//    }
//
//    private KieSession initKieSession() throws IOException {
//        InputStream template = DeliveryTest.class.getResourceAsStream("/rules/delivery/busy_deliverer.drt");
//        InputStream data = DeliveryTest.class.getResourceAsStream("/rules/delivery/busy_deliverer_rules.xls");
//
//        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
//        String drl = readDRLFileAsString();
//        drl += converter.compile(data, template, 3, 2);
//
//        KieSession kieSession = createKieSessionFromDRL(drl);
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
//
//    private String readDRLFileAsString() throws IOException {
//        try (InputStream inputStream = DeliveryTest.class.getResourceAsStream("/rules/delivery/delivery.drl")) {
//            assert inputStream != null;
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                return stringBuilder.toString();
//            }
//        }
//    }
//}
