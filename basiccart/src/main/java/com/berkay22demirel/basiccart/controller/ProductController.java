package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.service.IProductService;

@Controller
public class ProductController {

	@Autowired
	IProductService productService;

	public void addProduct(Product product) {
		productService.addProduct(product);
	}

	public void updateProduct(Product product) {
		productService.updateProduct(product);
	}

	public void deleteProduct(Product product) {
		productService.deleteProduct(product);
	}

	public List<Product> getAllProduct() {
		return productService.getAllProducts();
	}

}
