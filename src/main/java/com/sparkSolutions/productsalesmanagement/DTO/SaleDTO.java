package com.sparkSolutions.productsalesmanagement.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class SaleDTO {
    private Long id;
    private int quantity;
    private Date saleDate;
    private Long productId;
}

