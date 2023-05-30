package com.ftn.sbnz.service.services.employee;

import com.ftn.sbnz.model.models.RestaurantEmployee;
import com.ftn.sbnz.service.repository.RestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveEmployee {
    private final RestaurantEmployeeRepository restaurantEmployeeRepository;

    @Transactional(readOnly = false)
    public RestaurantEmployee execute(final RestaurantEmployee restaurantEmployee) {
        return restaurantEmployeeRepository.save(restaurantEmployee);
    }
}
