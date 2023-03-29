package com.enigma.ecommerce.repository;

import com.enigma.ecommerce.model.Admin;
import com.enigma.ecommerce.model.Brand;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    List<Admin> findAll(Specification specification);
}
