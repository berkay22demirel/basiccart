package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public interface IShoppingCartService extends IService {

	void addShoppingCart(ShoppingCart shoppingCart);

	void updateShoppingCart(ShoppingCart shoppingCart);

	void deleteShoppingCart(ShoppingCart shoppingCart);

	List<ShoppingCart> getAllShoppingCarts();

	ShoppingCartItem findMatchingItem(List<ShoppingCartItem> shoppingCartItems, Product product);

	List<Campaign> findApplicableCampaigns(ShoppingCart shoppingCart, List<Campaign> campaigns);

	void applyDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns);

	boolean applyCoupon(ShoppingCart shoppingCart, Coupon coupon);

}
