package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.accountAssignments")
    List<Product> findAll();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.accountAssignments WHERE p.id = :id")
    Optional<Product> findById(Long id);
}
