package com.sparkSolutions.productsalesmanagement.service;

import java.util.List;

import com.sparkSolutions.productsalesmanagement.DTO.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
