package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.ShoppingCart;

public interface IShoppingCartService extends IService {

	void addShoppingCart(ShoppingCart shoppingCart);

	void updateShoppingCart(ShoppingCart shoppingCart);

	void deleteShoppingCart(ShoppingCart shoppingCart);

	List<ShoppingCart> getAllShoppingCarts();

}
