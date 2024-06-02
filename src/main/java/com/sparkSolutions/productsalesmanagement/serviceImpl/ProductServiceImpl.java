package com.sparkSolutions.productsalesmanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparkSolutions.productsalesmanagement.DTO.ProductDTO;
import com.sparkSolutions.productsalesmanagement.entity.Product;
import com.sparkSolutions.productsalesmanagement.exception.ProductNotFoundException;
import com.sparkSolutions.productsalesmanagement.repository.ProductRepository;
import com.sparkSolutions.productsalesmanagement.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Update existingProduct with data from productDTO
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}

