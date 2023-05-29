package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`user`")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SuperBuilder
public class User extends BaseEntity {
    @Column(name = "email", nullable = false)
    String email;

    @ManyToOne
    @JsonManagedReference
    Role role;

    @Column(name = "password_hash", nullable = false, length = 60)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    String passwordHash;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "surname", nullable = false)
    String surname;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @Column(name = "profile_picture")
    String profilePicture;

    @Column(name = "active")
    @Value("true")
    boolean active;

    @Column(name = "blocked")
    @Value("false")
    boolean blocked;

    @OneToMany(mappedBy = "user")
    List<Address> deliveryAddresses = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    List<Report> reports = new ArrayList<>();

    @Column(name = "discount")
    @Value("0.0")
    Double discount;
}
