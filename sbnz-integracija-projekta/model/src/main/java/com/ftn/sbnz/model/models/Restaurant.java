package com.ftn.sbnz.model.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @Column(name="closed")
    boolean closed;

    boolean isNew = false;

    boolean isPopular = false;

    boolean isTopRated = false;

    boolean isBadRated = false;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    RestaurantOwner owner;

    @OneToMany(mappedBy = "restaurant")
    List<RestaurantEmployee> employees;

    @OneToMany(mappedBy = "restaurant")
    List<MenuItem> items;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<Review> reviews;

    public Double getRating() {
        return reviews.stream()
                .filter(review -> review.getRestaurantRating() != null)
                .mapToDouble(Review::getRestaurantRating)
                .average()
                .orElse(0.0);    }
}
