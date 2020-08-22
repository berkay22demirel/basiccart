package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Product;

public interface IProductService extends IService {

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(Product product);

	List<Product> getAllProducts();

}
