package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public interface IShoppingCartItemService extends IService {

	void addShoppingCartItem(ShoppingCartItem shoppingCartItem);

	void updateShoppingCartItem(ShoppingCartItem shoppingCartItem);

	void deleteShoppingCartItem(long id);

	List<ShoppingCartItem> getAllCategories();

}
