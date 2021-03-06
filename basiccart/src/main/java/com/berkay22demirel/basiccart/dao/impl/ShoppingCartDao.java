package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.entity.ShoppingCart;

@Repository
public class ShoppingCartDao extends DaoSupport<ShoppingCart> implements IShoppingCartDao {

	public ShoppingCartDao() {
		super(ShoppingCart.class);
	}

}
