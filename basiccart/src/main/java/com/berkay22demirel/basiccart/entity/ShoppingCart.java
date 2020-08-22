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

	public void addItem(Product product, int quantity) {
		boolean isExist = false;
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().equals(product)) {
				shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
			shoppingCartItems.add(shoppingCartItem);
		}
		totalAmount += (product.getPrice() * quantity);
	}

	public void applyDiscount(List<Campaign> campaigns) {
		this.campaigns = campaigns;

	}

	public void applyCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

}
