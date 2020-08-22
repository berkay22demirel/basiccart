package com.berkay22demirel.basiccart.model;

public class ShoppingCartItem extends BaseModel {

	private Product product;
	private int quantity;

	public ShoppingCartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

}
