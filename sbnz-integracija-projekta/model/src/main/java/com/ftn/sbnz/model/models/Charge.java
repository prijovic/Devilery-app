package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "charge")
public class Charge extends BaseEntity {

    String currency = "EUR";

    @Column(name = "product_cost")
    Double productsCost;

    @Column(name = "services_fee")
    Double servicesFee;

    @Column(name = "delivery_fee")
    Double deliveryFee;

    @Column(name = "user_discount")
    Double userDiscount;

    @Column(name = "discount_value")
    Double discountValue;

    @Column(name = "total")
    Double total;
}
