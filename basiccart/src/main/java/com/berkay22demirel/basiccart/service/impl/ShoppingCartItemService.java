package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.IShoppingCartItemDao;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IShoppingCartItemService;

import lombok.Setter;

@Service
@Setter
public class ShoppingCartItemService implements IShoppingCartItemService {

	@Autowired
	IShoppingCartItemDao shoppingCartItemDao;

	@Override
	public long addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		return shoppingCartItemDao.add(shoppingCartItem);

	}

	@Override
	public long updateShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		return shoppingCartItemDao.update(shoppingCartItem);

	}

	@Override
	public long deleteShoppingCartItem(long id) {
		return shoppingCartItemDao.delete(id);

	}

	@Override
	public List<ShoppingCartItem> getAllShoppingCartItems() {
		return shoppingCartItemDao.findAll();
	}

}
