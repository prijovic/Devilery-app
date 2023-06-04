package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Role(Role.Type.EVENT)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "restaurant")
@Timestamp("createdOn")
public class Restaurant extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    Date createdOn;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "picture", nullable = false)
    String picture;

    @Embedded
    WorkingHours workingHours;

    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "min_order", nullable = false)
    Double minOrder;

    @Column(name = "min_prep", nullable = false)
    Integer minPrep;

    @Column(name = "max_prep", nullable = false)
    Integer maxPrep;

    @Column(name = "closed")
    boolean closed;

    @Transient
    boolean isNew = false;

    @Transient
    boolean isPopular = false;

    @Transient
    boolean isWellRated = false;

    @Transient
    boolean isBadRated = false;

    @Transient
    Set<MenuItemType> specializedTypes;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    RestaurantOwner owner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<RestaurantEmployee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
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
