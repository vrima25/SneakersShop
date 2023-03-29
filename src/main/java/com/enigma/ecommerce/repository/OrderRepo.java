package com.enigma.ecommerce.repository;

import com.enigma.ecommerce.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAll(Specification specification);

    List<Order> findByOrderDate(String date);
}
