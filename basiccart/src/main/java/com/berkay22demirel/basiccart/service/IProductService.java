package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Product;

public interface IProductService extends IService {

	long addProduct(Product product);

	long updateProduct(Product product);

	long deleteProduct(long id);

	List<Product> getAllProducts();

}
