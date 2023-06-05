package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, UUID> {
}