//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.*;
//import com.ftn.sbnz.service.config.KieConfiguration;
//import org.drools.core.ClassObjectFilter;
//import org.junit.jupiter.api.Test;
//import org.kie.api.runtime.KieSession;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.UUID;
//
//public class BackwardTest {
//
//    @Test
//    public void testiranjteTest() throws IOException {
////        KieSession kieSession = new KieConfiguration().createKieSession();
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(UUID.randomUUID());
//        restaurant.setName("IMe dve reci");
//        restaurant.setDescription("koliko ludih reci ima ovde da ne verujes Da Dve");
//        MenuItem menuItem = new MenuItem();
//        menuItem.setType(MenuItemType.ASIAN);
//        menuItem.setName("dVE LUDE PILETINE");
//        menuItem.setDescription("joker je to u dvoje");
//        menuItem.setId(UUID.randomUUID());
//        restaurant.getItems().add(menuItem);
//
//        for (String word : restaurant.getName().toLowerCase().split(" ")) {
//            kieSession.insert(new Link(word, restaurant.getId().toString()));
//        }
//
//        for (String word : restaurant.getDescription().toLowerCase().split(" ")) {
//            kieSession.insert(new Link(word, restaurant.getId().toString()));
//        }
//
//        for (MenuItem item : restaurant.getItems()) {
//            kieSession.insert(new Link(item.getId().toString(), restaurant.getId().toString()));
//            for (String word : item.getName().toLowerCase().split(" ")) {
//                kieSession.insert(new Link(word, item.getId().toString()));
//            }
//
//            for (String word : item.getDescription().toLowerCase().split(" ")) {
//                kieSession.insert(new Link(word, item.getId().toString()));
//            }
//
//            kieSession.insert(new Link(item.getType().toString().toLowerCase(), item.getId().toString()));
//        }
//
//        kieSession.insert("ludih");
//        kieSession.insert("dve");
//        kieSession.insert("piletine");
//        kieSession.insert(restaurant.getId());
//
//        kieSession.fireAllRules();
//
//        List<SearchResult> searchResults = new ArrayList<>((Collection<SearchResult>) kieSession.getObjects(new ClassObjectFilter(SearchResult.class)));
//
//        System.out.println(searchResults.size());
//    }
//}
