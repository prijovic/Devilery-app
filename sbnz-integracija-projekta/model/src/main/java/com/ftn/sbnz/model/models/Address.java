package com.ftn.sbnz.model.models;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    User user;
}
