package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.berkay22demirel.basiccart.dao.IProductDao;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.service.IProductService;

public class ProductService implements IProductService {

	@Autowired
	private IProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.add(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.update(product);

	}

	@Override
	public void deleteProduct(Product product) {
		productDao.delete(product);

	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

}
