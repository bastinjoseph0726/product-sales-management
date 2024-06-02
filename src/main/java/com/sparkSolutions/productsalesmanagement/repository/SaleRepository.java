package com.sparkSolutions.productsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparkSolutions.productsalesmanagement.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByProductId(Long productId);
}