package com.berkay22demirel.basiccart.dao;

import java.util.HashMap;
import java.util.Map;

import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public class Database {

	private static Map<Long, Object> campaign = new HashMap<>();
	private static Map<Long, Object> category = new HashMap<>();
	private static Map<Long, Object> coupon = new HashMap<>();
	private static Map<Long, Object> product = new HashMap<>();
	private static Map<Long, Object> shoppingCart = new HashMap<>();
	private static Map<Long, Object> shoppingCartItem = new HashMap<>();

	static {
		Category category1 = new Category(1, "kitap");
		Category category2 = new Category(2, "meyve");
		Campaign campaignRate = new Campaign(1, category1, 15.0, 3, DiscountType.RATE);
		Campaign campaignAmount = new Campaign(2, category2, 1.0, 5, DiscountType.AMOUNT);
		Coupon coupon1 = new Coupon(1, 50.0, 5.0, DiscountType.AMOUNT);
		Product product1 = new Product(1, "elma", 5.0, category2);
		category.put(1l, category1);
		category.put(2l, category2);
		campaign.put(1l, campaignRate);
		campaign.put(2l, campaignAmount);
		coupon.put(1l, coupon1);
		product.put(1l, product1);
	}

	public static Map<Long, Object> getTable(Object obj) {
		if (obj.equals(Campaign.class)) {
			return campaign;
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
