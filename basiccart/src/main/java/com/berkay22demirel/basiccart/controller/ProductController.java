package com.berkay22demirel.basiccart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.service.IProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	IProductService productService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll")
	public ResponseEntity<Object> getAllProduct() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

}
