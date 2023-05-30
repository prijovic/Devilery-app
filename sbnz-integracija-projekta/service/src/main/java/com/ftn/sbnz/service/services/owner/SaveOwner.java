package com.ftn.sbnz.service.services.owner;

import com.ftn.sbnz.model.models.RestaurantOwner;
import com.ftn.sbnz.service.repository.RestaurantOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveOwner {
    private final RestaurantOwnerRepository restaurantOwnerRepository;

    public RestaurantOwner execute(RestaurantOwner restaurantOwner) {
        return restaurantOwnerRepository.save(restaurantOwner);
    }
}
