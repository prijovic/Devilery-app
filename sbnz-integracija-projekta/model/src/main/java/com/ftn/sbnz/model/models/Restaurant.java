package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    LocalDateTime createdOn;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Embedded
    WorkingHours workingHours;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "min_order", nullable = false)
    Double minOrder;

    @Column(name = "closed")
    boolean closed;

    boolean isNew = false;

    boolean isPopular = false;

    boolean isWellRated = false;

    boolean isBadRated = false;

    @Transient
    Set<MenuItemType> specializedTypes;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    RestaurantOwner owner;

    @OneToMany(mappedBy = "restaurant")
    List<RestaurantEmployee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    List<MenuItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    public Double getRating() {
        return reviews.stream()
                .filter(review -> review.getRestaurantRating() != null)
                .mapToDouble(Review::getRestaurantRating)
                .average()
                .orElse(0.0);
    }

    public boolean isSpecializedForType(MenuItemType type) {
        return specializedTypes.contains(type);
    }
}
