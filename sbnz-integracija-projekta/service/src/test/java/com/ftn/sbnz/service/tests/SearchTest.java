//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.MenuItem;
//import com.ftn.sbnz.model.models.MenuItemType;
//import com.ftn.sbnz.model.models.Recommendation;
//import com.ftn.sbnz.model.models.Restaurant;
//import com.ftn.sbnz.service.config.KieConfiguration;
//import org.drools.core.ClassObjectFilter;
//import org.junit.jupiter.api.Test;
//import org.kie.api.runtime.KieSession;
//
//import java.io.IOException;
//import java.util.Collection;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class SearchTest extends RestaurantTest {
//
//    @Test
//    public void testRestaurantSpecialization() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant = createRestaurant("Restaurant 1", "08:00:00", "23:30:00");
//
//        for (int i = 0; i < 4; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.PIZZA);
//            restaurant.getItems().add(item);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.PASTA);
//            restaurant.getItems().add(item);
//        }
//
//        for (int i = 0; i < 6; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.ITALIAN);
//            restaurant.getItems().add(item);
//        }
//
//        for (int i = 0; i < 3; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.CHICKEN);
//            restaurant.getItems().add(item);
//        }
//
//        kieSession.insert(MenuItemType.CHICKEN);
//        kieSession.insert(restaurant);
//
//        kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
//        kieSession.fireAllRules();
//
//        assertEquals(3, restaurant.getSpecializedTypes().size());
//        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.PIZZA));
//        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.PASTA));
//        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.ITALIAN));
//        assertFalse(restaurant.getSpecializedTypes().contains(MenuItemType.CHICKEN));
//
//        kieSession.dispose();
//    }
//
//    @Test
//    public void testRestaurantSearchBySpecialization() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant1 = createRestaurant("Restaurant 1", "08:00:00", "23:30:00");
//        Restaurant restaurant2 = createRestaurant("Restaurant 2", "08:00:00", "23:30:00");
//        Restaurant restaurant3 = createRestaurant("Restaurant 3", "08:00:00", "23:30:00");
//        Restaurant restaurant4 = createRestaurant("Restaurant 4", "08:00:00", "23:30:00");
//
//        for (int i = 0; i < 5; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.ITALIAN);
//            restaurant1.getItems().add(item);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.ITALIAN);
//            restaurant2.getItems().add(item);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.ITALIAN);
//            restaurant3.getItems().add(item);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            MenuItem item = new MenuItem();
//            item.setType(MenuItemType.BURGER);
//            restaurant4.getItems().add(item);
//        }
//
//        kieSession.insert(restaurant1);
//        kieSession.insert(restaurant2);
//        kieSession.insert(restaurant3);
//        kieSession.insert(restaurant4);
//        kieSession.insert(MenuItemType.ITALIAN);
//
//        kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
//        kieSession.fireAllRules();
//
//        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));
//
//        assertTrue(restaurant1.getSpecializedTypes().contains(MenuItemType.ITALIAN));
//        assertFalse(restaurant1.getSpecializedTypes().contains(MenuItemType.BURGER));
//
//        assertTrue(restaurant2.getSpecializedTypes().contains(MenuItemType.ITALIAN));
//        assertFalse(restaurant2.getSpecializedTypes().contains(MenuItemType.BURGER));
//
//        assertTrue(restaurant3.getSpecializedTypes().contains(MenuItemType.ITALIAN));
//        assertFalse(restaurant3.getSpecializedTypes().contains(MenuItemType.BURGER));
//
//        assertTrue(restaurant4.getSpecializedTypes().contains(MenuItemType.BURGER));
//
//        assertEquals(3, recommendations.size());
//        for (Recommendation recommendation : recommendations) {
//            Restaurant restaurant = recommendation.getRestaurant();
//            assertTrue(restaurant.equals(restaurant1) || restaurant.equals(restaurant2) || restaurant.equals(restaurant3));
//        }
//        kieSession.dispose();
//    }
//
//
//}
