package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, UUID> {
}