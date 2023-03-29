package com.enigma.ecommerce.repository;

import com.enigma.ecommerce.model.Sneaker;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SneakerRepo extends JpaRepository<Sneaker, Integer> {
    List<Sneaker> findAll(Specification specification);
}
