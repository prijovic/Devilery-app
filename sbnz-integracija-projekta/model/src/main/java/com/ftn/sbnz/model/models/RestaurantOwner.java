package com.ftn.sbnz.model.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "restaurant_owner")
public class RestaurantOwner extends User {

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants = new ArrayList<>();

    public List<Restaurant> getRestaurants() {
        if (restaurants == null) {
            restaurants = new ArrayList<>();
        }
        return restaurants;
    }
}
