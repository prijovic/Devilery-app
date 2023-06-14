package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "deliverer")
public class Deliverer extends User {

    @Column(name = "online")
    @Value("false")
    boolean online;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    DelivererStatus status;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    DelivererType type;

    @OneToMany(mappedBy = "deliverer")
    @JsonManagedReference
    List<Order> ordersDelivered = new ArrayList<>();

    @OneToMany(mappedBy = "deliverer")
    @JsonManagedReference
    List<Review> gotReviews = new ArrayList<>();

    @OneToMany(mappedBy = "deliverer")
    @JsonManagedReference
    List<Report> gotReports = new ArrayList<>();

    @Transient
    boolean isWellRated = false;

    @Transient
    boolean isBadRated = false;

    public Double getRating() {
        return gotReviews.stream()
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
