package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProducts() { 
		return productRepository.findAll(); 
		}
	
	public Product saveProduct(Product product) { 
		return productRepository.save(product);
	}
	
}
