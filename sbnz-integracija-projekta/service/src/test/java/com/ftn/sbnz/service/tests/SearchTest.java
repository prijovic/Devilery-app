package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.model.models.MenuItemType;
import com.ftn.sbnz.model.models.Recommendation;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.service.services.kie.GetRestaurantKieSession;
import org.drools.core.ClassObjectFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


public class SearchTest extends RestaurantTest {

    @Test
    public void testRestaurantSpecialization() {
        KieSession kieSession = new GetRestaurantKieSession().execute();

        Restaurant restaurant = new Restaurant();

        for (int i = 0; i < 4; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.PIZZA);
            restaurant.getItems().add(item);
        }

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.PASTA);
            restaurant.getItems().add(item);
        }

        for (int i = 0; i < 6; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.ITALIAN);
            restaurant.getItems().add(item);
        }

        for (int i = 0; i < 3; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.CHICKEN);
            restaurant.getItems().add(item);
        }

        kieSession.insert(MenuItemType.CHICKEN);
        kieSession.insert(restaurant);

        kieSession.fireAllRules();

        assertEquals(3, restaurant.getSpecializedTypes().size());
        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.PIZZA));
        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.PASTA));
        assertTrue(restaurant.getSpecializedTypes().contains(MenuItemType.ITALIAN));
        assertFalse(restaurant.getSpecializedTypes().contains(MenuItemType.CHICKEN));

        kieSession.dispose();
    }

    @Test
    public void testRestaurantSearchBySpecialization() {
        KieSession kieSession = new GetRestaurantKieSession().execute();

        Restaurant restaurant1 = getPopularNewRestaurant();
        Restaurant restaurant2 = getRatedRestaurant(now.minusMonths(2), 1, 2.49, 10);
        Restaurant restaurant3 = getRatedRestaurant(now.minusMonths(2), 4.5, 5, 10);
        Restaurant restaurant4 = getPopularNewRestaurant();

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.ITALIAN);
            restaurant1.getItems().add(item);
        }

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.ITALIAN);
            restaurant2.getItems().add(item);
        }

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.ITALIAN);
            restaurant3.getItems().add(item);
        }

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem();
            item.setType(MenuItemType.BURGER);
            restaurant4.getItems().add(item);
        }

        kieSession.insert(restaurant1);
        kieSession.insert(restaurant2);
        kieSession.insert(restaurant3);
        kieSession.insert(restaurant4);
        kieSession.insert(MenuItemType.ITALIAN);

        kieSession.fireAllRules();

        Collection<Recommendation> recommendations = (Collection<Recommendation>) kieSession.getObjects(new ClassObjectFilter(Recommendation.class));

        assertTrue(restaurant1.getSpecializedTypes().contains(MenuItemType.ITALIAN));
        assertFalse(restaurant1.getSpecializedTypes().contains(MenuItemType.BURGER));
        assertTrue(restaurant1.isPopular());
        assertTrue(restaurant1.isNew());
        assertFalse(restaurant1.isBadRated());

        assertTrue(restaurant2.getSpecializedTypes().contains(MenuItemType.ITALIAN));
        assertFalse(restaurant2.getSpecializedTypes().contains(MenuItemType.BURGER));
        assertFalse(restaurant2.isPopular());
        assertFalse(restaurant2.isNew());
        assertTrue(restaurant2.isBadRated());

        assertTrue(restaurant3.getSpecializedTypes().contains(MenuItemType.ITALIAN));
        assertFalse(restaurant3.getSpecializedTypes().contains(MenuItemType.BURGER));
        assertFalse(restaurant3.isPopular());
        assertFalse(restaurant3.isNew());
        assertFalse(restaurant3.isBadRated());

        assertTrue(restaurant4.getSpecializedTypes().contains(MenuItemType.BURGER));
        assertTrue(restaurant4.isPopular());
        assertTrue(restaurant4.isNew());
        assertFalse(restaurant4.isBadRated());

        assertEquals(2, recommendations.size());
        for (Recommendation recommendation : recommendations) {
            Restaurant restaurant = recommendation.getRestaurant();
            assertTrue(restaurant.equals(restaurant1) || restaurant.equals(restaurant4));
        }
        kieSession.dispose();
    }


}
