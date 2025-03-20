package com.example.api.repository;


import com.example.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByOrderByCreateDateDesc();
}