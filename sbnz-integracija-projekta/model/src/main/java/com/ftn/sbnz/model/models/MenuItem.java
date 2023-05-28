package com.ftn.sbnz.model.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_item")
public class MenuItem extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    LocalDateTime createdOn;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "picture", nullable = false)
    String picture;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "available")
    @Value("true")
    boolean available;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    MenuItemType type;

    @ElementCollection(targetClass = Allergen.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "menu_item_allergens")
    @Column(name = "allergens")
    List<Allergen> allergens;

    @ManyToMany(mappedBy = "items")
    Set<Order> orders;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    Restaurant restaurant;
}
