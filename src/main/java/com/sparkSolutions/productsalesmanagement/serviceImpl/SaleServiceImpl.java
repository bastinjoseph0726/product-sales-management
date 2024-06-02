package com.sparkSolutions.productsalesmanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparkSolutions.productsalesmanagement.DTO.SaleDTO;
import com.sparkSolutions.productsalesmanagement.entity.Sale;
import com.sparkSolutions.productsalesmanagement.exception.SaleNotFoundException;
import com.sparkSolutions.productsalesmanagement.repository.SaleRepository;
import com.sparkSolutions.productsalesmanagement.service.SaleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with id: " + id));
    }

    @Override
    public SaleDTO addSale(SaleDTO saleDTO) {
        Sale sale = modelMapper.map(saleDTO, Sale.class);
        Sale savedSale = saleRepository.save(sale);
        return modelMapper.map(savedSale, SaleDTO.class);
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with id: " + id));

        // Update existingSale with data from saleDTO
        existingSale.setQuantity(saleDTO.getQuantity());
        existingSale.setSaleDate(saleDTO.getSaleDate());

        Sale updatedSale = saleRepository.save(existingSale);
        return modelMapper.map(updatedSale, SaleDTO.class);
    }

    @Override
    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new SaleNotFoundException("Sale not found with id: " + id);
        }
        saleRepository.deleteById(id);
    }

	@Override
	public double getTotalRevenue() {
		List<Sale> sales = saleRepository.findAll();
		double totalRevenue = 0;
		for (Sale sale : sales) {
			totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
		}
		return totalRevenue;
	}

	@Override
	public double getRevenueByProduct(Long productId) {
		List<Sale> sales = saleRepository.findByProductId(productId);
		double revenueByProduct = 0;
		for (Sale sale : sales) {
			revenueByProduct += sale.getQuantity() * sale.getProduct().getPrice();
		}
		return revenueByProduct;
	}
}
