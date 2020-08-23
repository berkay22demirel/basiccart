package com.berkay22demirel.basiccart.dao;

import java.util.HashMap;
import java.util.Map;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public class Database {

	private static Map<Long, Object> campaing = new HashMap<>();
	private static Map<Long, Object> category = new HashMap<>();
	private static Map<Long, Object> coupon = new HashMap<>();
	private static Map<Long, Object> product = new HashMap<>();
	private static Map<Long, Object> shoppingCart = new HashMap<>();
	private static Map<Long, Object> shoppingCartItem = new HashMap<>();

	public static Map<Long, Object> getTable(Object obj) {
		if (obj.equals(Campaign.class)) {
			return campaing;
		}
		if (obj.equals(Category.class)) {
			return category;
		}
		if (obj.equals(Coupon.class)) {
			return coupon;
		}
		if (obj.equals(Product.class)) {
			return product;
		}
		if (obj.equals(ShoppingCart.class)) {
			return shoppingCart;
		}
		if (obj.equals(ShoppingCartItem.class)) {
			return shoppingCartItem;
		}
		return null;
	}

}
