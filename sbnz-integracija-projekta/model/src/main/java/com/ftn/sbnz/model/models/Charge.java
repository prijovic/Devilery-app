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
    @Column(name = "amount")
    Double amount;

    String currency = "EUR";

    @Column(name = "charged")
    Boolean charged = false;
}
