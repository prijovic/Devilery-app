package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "deliverer")
@AllArgsConstructor
public class Deliverer extends User {

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    DelivererStatus status;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    DelivererType type;

    @JsonManagedReference
    @OneToMany(mappedBy = "deliverer", fetch = FetchType.LAZY)
    List<Order> ordersDelivered = new ArrayList<>();

    @OneToMany(mappedBy = "deliverer", cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "deliverer", cascade = CascadeType.ALL)
    List<Report> reports = new ArrayList<>();

    boolean isWellRated = false;

    boolean isBadRated = false;

    public Double getRating() {
        return reviews.stream()
                .filter(review -> review.getDelivererRating() != null)
                .mapToDouble(Review::getDelivererRating)
                .average()
                .orElse(0.0);
    }

    public Order getCurrentOrder() {
        return ordersDelivered
                .stream()
                .filter(order -> order.getStatus().equals(OrderStatus.BEING_DELIVERED))
                .findFirst().orElse(null);
    }
}
