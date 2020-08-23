package com.berkay22demirel.basiccart.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartItem extends BaseEntity {

	private Product product;
	private int quantity;
	private double campaignDiscountAmountPerProduct;
	private double couponDiscountAmountPerProduct;

	public ShoppingCartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

}
