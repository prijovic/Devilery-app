//package com.ftn.sbnz.service.tests;
//
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
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class RecommendationTest extends RestaurantTest {
//
//    @Test
//    public void testNewRestaurantRule() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant = getNewRestaurant();
//
//        kieSession.insert(restaurant);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(restaurant.isNew());
//    }
//
//    @Test
//    public void testPopularRestaurantRule() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant1 = getPopularRestaurantByReviewNumber();
//        Restaurant restaurant2 = getPopularNewRestaurant();
//
//        kieSession.insert(restaurant1);
//        kieSession.insert(restaurant2);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(restaurant1.isPopular());
//        assertTrue(restaurant2.isPopular());
//    }
//
//    @Test
//    public void testWellRatedRestaurantRule() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant = getRatedRestaurant(now.minusMonths(2), 4.5, 5, 10);
//
//        kieSession.insert(restaurant);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(restaurant.isWellRated());
//        assertFalse(restaurant.isBadRated());
//    }
//
//    @Test
//    public void testBadlyRatedRestaurantRule() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant = getRatedRestaurant(now.minusMonths(2), 1, 2.49, 10);
//
//        kieSession.insert(restaurant);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(restaurant.isBadRated());
//        assertFalse(restaurant.isWellRated());
//    }
//
//    @Test
//    public void testRestaurantRecommendation() throws IOException {
//        KieSession kieSession = new KieConfiguration().createKieSession();
//        Restaurant restaurant1 = createRestaurant("Restaurant A", "09:00:00", "18:00:00");
//        kieSession.insert(restaurant1);
//
//        Restaurant restaurant2 = createRestaurant("Restaurant B", "10:30:00", "20:00:00");
//        kieSession.insert(restaurant2);
//
//        Restaurant restaurant3 = createRestaurant("Restaurant C", "08:00:00", "16:30:00");
//        kieSession.insert(restaurant3);
//
//        Restaurant restaurant4 = createRestaurant("Restaurant D", "12:00:00", "22:30:00");
//        kieSession.insert(restaurant4);
//
//        Restaurant restaurant5 = createRestaurant("Restaurant E", "07:30:00", "15:00:00");
//        kieSession.insert(restaurant5);
//
//        Restaurant restaurant6 = createRestaurant("Restaurant F", "11:00:00", "19:30:00");
//        kieSession.insert(restaurant6);
//
//        Restaurant restaurant7 = createRestaurant("Restaurant G", "08:30:00", "17:00:00");
//        kieSession.insert(restaurant7);
//
//        Restaurant restaurant8 = createRestaurant("Restaurant H", "09:30:00", "18:30:00");
//        kieSession.insert(restaurant8);
//
//        Restaurant restaurant9 = createRestaurant("Restaurant I", "13:30:00", "21:00:00");
//        kieSession.insert(restaurant9);
//
//        Restaurant restaurant10 = createRestaurant("Restaurant J", "06:00:00", "14:30:00");
//        kieSession.insert(restaurant10);
//
//        Restaurant restaurant11 = createRestaurant("Restaurant K", "10:00:00", "19:00:00");
//        kieSession.insert(restaurant11);
//
//        Restaurant restaurant12 = createRestaurant("Restaurant L", "08:30:00", "16:00:00");
//        kieSession.insert(restaurant12);
//
//        Restaurant restaurant13 = createRestaurant("Restaurant M", "11:30:00", "20:30:00");
//        kieSession.insert(restaurant13);
//
//        Restaurant restaurant14 = createRestaurant("Restaurant N", "07:00:00", "15:30:00");
//        kieSession.insert(restaurant14);
//
//        Restaurant restaurant15 = createRestaurant("Restaurant O", "12:30:00", "22:00:00");
//        kieSession.insert(restaurant15);
//
//        Restaurant restaurant16 = createRestaurant("Restaurant P", "09:30:00", "18:30:00");
//        kieSession.insert(restaurant16);
//
//        Restaurant restaurant17 = createRestaurant("Restaurant Q", "07:30:00", "17:00:00");
//        kieSession.insert(restaurant17);
//
//        Restaurant restaurant18 = createRestaurant("Restaurant R", "10:00:00", "20:30:00");
//        kieSession.insert(restaurant18);
//
//        Restaurant restaurant19 = createRestaurant("Restaurant S", "08:00:00", "16:30:00");
//        kieSession.insert(restaurant19);
//
//        Restaurant restaurant20 = createRestaurant("Restaurant T", "11:30:00", "21:00:00");
//        kieSession.insert(restaurant20);
//
//
//        kieSession.fireAllRules();
//
//        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));
//
//        kieSession.dispose();
//
//        for (Recommendation recommendation : recommendations) {
//            System.out.println(recommendation.getRestaurant().getName());
//        }
//    }
//
//
//}
