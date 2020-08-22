package com.berkay22demirel.basiccart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartItem extends BaseModel {

	private Product product;
	private int quantity;

	public ShoppingCartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

}
