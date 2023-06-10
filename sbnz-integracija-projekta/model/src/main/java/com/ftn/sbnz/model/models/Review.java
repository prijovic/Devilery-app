package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "review")
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverer_id")
    @JsonBackReference
    Deliverer deliverer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    Restaurant restaurant;

    @Column(name = "deliverer_rating")
    Double delivererRating;

    @Column(name = "deliverer_comment")
    String delivererComment;

    @Column(name = "restaurant_rating")
    Double restaurantRating;

    @Column(name = "restaurant_comment")
    String restaurantComment;
}
