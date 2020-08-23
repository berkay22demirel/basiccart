package com.berkay22demirel.basiccart.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCart {

	private List<ShoppingCartItem> shoppingCartItems;
	private List<Campaign> campaigns;
	private Coupon coupon;
	private double totalAmount;
	private double campaignDiscountAmount;
	private double couponDiscountAmount;
	private double deliveryCost;

	public ShoppingCart() {
		super();
		shoppingCartItems = new ArrayList<>();
		totalAmount = 0;
		campaignDiscountAmount = 0;
		couponDiscountAmount = 0;
	}

}
