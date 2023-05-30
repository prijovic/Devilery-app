package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "`order`")
public class Order extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverer_id")
    @JsonBackReference
    Deliverer deliverer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    User customer;

    @Column(name = "status", nullable = false)
    OrderStatus status;

    @Column(name = "delivery_distance", nullable = false)
    Double deliveryDistance;

    @Column(name = "discount")
    Double discount;

    @Column(name = "tip")
    Double tip;

    @Column(name = "rejection_reason")
    String rejectionReason;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "unsuccessful_delivery_reason")
    String unsuccessfulDeliveryReason;

    @ManyToMany
    @JoinTable(name = "items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    List<MenuItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "report_id")
    Report report;

    @OneToOne
    @JoinColumn(name = "charge_id")
    Charge charge;

    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;
}
