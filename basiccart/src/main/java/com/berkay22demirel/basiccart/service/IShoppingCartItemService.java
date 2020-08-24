package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public interface IShoppingCartItemService extends IService {

	long addShoppingCartItem(ShoppingCartItem shoppingCartItem);

	long updateShoppingCartItem(ShoppingCartItem shoppingCartItem);

	long deleteShoppingCartItem(long id);

	List<ShoppingCartItem> getAllCategories();

}
