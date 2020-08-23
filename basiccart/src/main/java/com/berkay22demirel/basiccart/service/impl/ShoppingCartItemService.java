package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.IShoppingCartItemDao;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IShoppingCartItemService;

@Service
public class ShoppingCartItemService implements IShoppingCartItemService {

	@Autowired
	IShoppingCartItemDao shoppingCartItemDao;

	@Override
	public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		shoppingCartItemDao.add(shoppingCartItem);

	}

	@Override
	public void updateShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		shoppingCartItemDao.update(shoppingCartItem);

	}

	@Override
	public void deleteShoppingCartItem(long id) {
		shoppingCartItemDao.delete(id);

	}

	@Override
	public List<ShoppingCartItem> getAllCategories() {
		return shoppingCartItemDao.findAll();
	}

}
