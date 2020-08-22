package com.berkay22demirel.basiccart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.entity.ShoppingCart;

@Service
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	IShoppingCartDao shoppingCartDao;

	@Override
	public void addShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.add(shoppingCart);

	}

	@Override
	public void updateShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.update(shoppingCart);

	}

	@Override
	public void deleteShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.delete(shoppingCart);

	}

	@Override
	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartDao.findAll();
	}

}
