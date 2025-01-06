package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;



import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;



@Controller
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@PostMapping
	public String saveProducts(@RequestBody Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
}
