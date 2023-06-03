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
@Table(name = "address")
public class Address extends BaseEntity {
    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "longitude", nullable = false)
    Double longitude;

    @Column(name = "latitude", nullable = false)
    Double latitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;
}
