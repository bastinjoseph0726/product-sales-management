package com.sparkSolutions.productsalesmanagement.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparkSolutions.productsalesmanagement.DTO.SaleDTO;
import com.sparkSolutions.productsalesmanagement.service.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public SaleDTO getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> addSale(@Valid @RequestBody SaleDTO saleDTO) throws URISyntaxException {
        SaleDTO createdSale = saleService.addSale(saleDTO);
        return ResponseEntity.created(new URI("/api/sales/" + createdSale.getId())).body(createdSale);
    }

    @PutMapping("/{id}")
    public SaleDTO updateSale(@PathVariable Long id, @Valid @RequestBody SaleDTO saleDTO) {
        return saleService.updateSale(id, saleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
    }

    @GetMapping("/revenue/total")
    public double getTotalRevenue() {
        return saleService.getTotalRevenue();
    }

    @GetMapping("/revenue/{productId}")
    public double getRevenueByProduct(@PathVariable Long productId) {
        return saleService.getRevenueByProduct(productId);
    }
}
