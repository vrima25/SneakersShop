package com.enigma.ecommerce.repository;

import com.enigma.ecommerce.model.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findAll(Specification specification);
}
