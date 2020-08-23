package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.IProductDao;
import com.berkay22demirel.basiccart.entity.Product;

@Repository
public class ProductDao extends DaoSupoort<Product> implements IProductDao {

	public ProductDao() {
		super(Product.class);
	}

}
