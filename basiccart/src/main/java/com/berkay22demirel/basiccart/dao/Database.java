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

	private static Map<Long, Campaign> campaing = new HashMap<>();
	private static Map<Long, Category> category = new HashMap<>();
	private static Map<Long, Coupon> coupon = new HashMap<>();
	private static Map<Long, Product> product = new HashMap<>();
	private static Map<Long, ShoppingCart> shoppingCart = new HashMap<>();
	private static Map<Long, ShoppingCartItem> shoppingCartItem = new HashMap<>();

	public static <T> Map<Long, T> getTable(Class<T> c) {
		if (c.equals(Campaign.class)) {
			return (Map<Long, T>) campaing;
		}
		if (c.equals(Category.class)) {
			return (Map<Long, T>) category;
		}
		if (c.equals(Coupon.class)) {
			return (Map<Long, T>) coupon;
		}
		if (c.equals(Product.class)) {
			return (Map<Long, T>) product;
		}
		if (c.equals(ShoppingCart.class)) {
			return (Map<Long, T>) shoppingCart;
		}
		if (c.equals(ShoppingCartItem.class)) {
			return (Map<Long, T>) shoppingCartItem;
		}
		return null;
	}

}
