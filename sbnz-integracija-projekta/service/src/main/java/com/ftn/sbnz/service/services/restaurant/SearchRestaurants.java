package com.ftn.sbnz.service.services.restaurant;

import com.ftn.sbnz.model.models.Link;
import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.SearchResult;
import com.ftn.sbnz.service.converter.RestaurantConverter;
import com.ftn.sbnz.service.dto.response.RestaurantResponse;
import com.ftn.sbnz.service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SearchRestaurants {
    private final KieSession kieSession;
    private final RestaurantRepository restaurantRepository;
    private final GetRestaurantById getRestaurantById;

    public List<RestaurantResponse> execute(String query) {
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.findAll()) {
            kieSession.insert(restaurant.getId());

            for (String word : restaurant.getName().toLowerCase().split(" ")) {
                kieSession.insert(new Link(word, restaurant.getId().toString()));
            }

            for (String word : restaurant.getDescription().toLowerCase().split(" ")) {
                kieSession.insert(new Link(word, restaurant.getId().toString()));
            }

            for (MenuItem item : restaurant.getItems()) {
                kieSession.insert(new Link(item.getId().toString(), restaurant.getId().toString()));
                for (String word : item.getName().toLowerCase().split(" ")) {
                    kieSession.insert(new Link(word, item.getId().toString()));
                }

                for (String word : item.getDescription().toLowerCase().split(" ")) {
                    kieSession.insert(new Link(word, item.getId().toString()));
                }

                kieSession.insert(new Link(item.getType().toString().toLowerCase(), item.getId().toString()));
            }
        }
        for (String word : query.toLowerCase().split(" ")) {
            kieSession.insert(word);
        }
        kieSession.fireAllRules();
        List<SearchResult> searchResults = new ArrayList<>((Collection<SearchResult>) kieSession.getObjects(new ClassObjectFilter(SearchResult.class)));
        Map<String, Integer> resultMap = new HashMap<>();

        for (SearchResult searchResult : searchResults) {
            String id = searchResult.getId().toString();
            int count = resultMap.getOrDefault(id, 0);
            resultMap.put(id, count + 1);
        }

        List<String> sortedIds = new ArrayList<>(resultMap.keySet());

        sortedIds.sort(Comparator.comparingInt(resultMap::get).reversed());

        for (String id : sortedIds) {
            UUID uuid = UUID.fromString(id);
            restaurantResponses.add(RestaurantConverter.toRestaurantResponse(getRestaurantById.execute(uuid)));
        }

        List<Object> objects = new ArrayList<>(kieSession.getObjects(new ClassObjectFilter(Object.class)));
        for (Object object : objects) {
            kieSession.delete(kieSession.getFactHandle(object));
        }
        
        return restaurantResponses;
    }

}
