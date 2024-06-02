package com.sparkSolutions.productsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparkSolutions.productsalesmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
