package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "deliverer")
public class Deliverer extends User {

    @JsonManagedReference
    @OneToMany(mappedBy = "deliverer", fetch = FetchType.LAZY)
    List<Order> orders_delivered;

    @OneToMany(mappedBy = "deliverer", cascade = CascadeType.ALL)
    List<Review> reviews;
}
