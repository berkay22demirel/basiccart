package com.berkay22demirel.basiccart.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends BaseModel {

	private List<ShoppingCartItem> shoppingCartItems;
	private List<Campaign> campaigns;
	private List<Coupon> coupons;
	private double totalAmount;
	private double campaignDiscountAmount;
	private double couponDiscountAmount;
	private double deliveryCost;
	private double fixedCost;

	public ShoppingCart() {
		super();
		shoppingCartItems = new ArrayList<>();
		campaigns = new ArrayList<>();
		coupons = new ArrayList<>();
	}

}
