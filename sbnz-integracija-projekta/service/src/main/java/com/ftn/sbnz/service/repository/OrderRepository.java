package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.model.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByRestaurantIdAndStatusIn(UUID restaurantId, List<OrderStatus> statuses);

    List<Order> findAllByDelivererAndStatusIn(Deliverer deliverer, List<OrderStatus> statuses);

    List<Order> findAllByCustomerAndStatusNotIn(User customer, List<OrderStatus> statuses);

    List<Order> findAllByCustomerAndStatusIn(User customer, List<OrderStatus> statuses);
}