package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.IProductDao;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDao productDao;

	@Override
	public long addProduct(Product product) {
		return productDao.add(product);
	}

	@Override
	public long updateProduct(Product product) {
		return productDao.update(product);

	}

	@Override
	public long deleteProduct(long id) {
		return productDao.delete(id);

	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

}
