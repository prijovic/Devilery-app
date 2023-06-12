package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.Deliverer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface DelivererRepository extends JpaRepository<Deliverer, UUID> {
}