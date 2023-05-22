package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order")
public class Order extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "deliverer_id")
    @JsonBackReference
    Deliverer deliverer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    User customer;

    @Column(name = "status", nullable = false)
    OrderStatus status;

    @Column(name = "rejection_reason")
    String rejectionReason;

    @Column(name = "unsuccessful_delivery_reason")
    String unsuccessfulDeliveryReason;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charge_id")
    Charge charge;

}
