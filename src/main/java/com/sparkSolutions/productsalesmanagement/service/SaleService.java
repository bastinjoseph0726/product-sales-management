package com.sparkSolutions.productsalesmanagement.service;

import java.util.List;

import com.sparkSolutions.productsalesmanagement.DTO.SaleDTO;

public interface SaleService {
    List<SaleDTO> getAllSales();
    SaleDTO getSaleById(Long id);
    SaleDTO addSale(SaleDTO saleDTO);
    SaleDTO updateSale(Long id, SaleDTO saleDTO);
    void deleteSale(Long id);
    double getTotalRevenue();
    double getRevenueByProduct(Long productId);
}

