package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;

public interface IShoppingCartService extends IService {

	long addShoppingCart(ShoppingCart shoppingCart);

	long updateShoppingCart(ShoppingCart shoppingCart);

	long deleteShoppingCart(long id);

	List<ShoppingCart> getAllShoppingCarts();

	ShoppingCart addItem(ShoppingCart shoppingCart, Product product, int quantity);

	ShoppingCart applyDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns);

	ShoppingCart applyCoupon(ShoppingCart shoppingCart, Coupon coupon);

}
