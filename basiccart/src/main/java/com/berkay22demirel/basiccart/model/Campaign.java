package com.berkay22demirel.basiccart.model;

import com.berkay22demirel.basiccart.constant.DiscountType;

public class Campaign extends BaseModel {

	private Category category;
	private double discount;
	private int minimumItemCount;
	private DiscountType discountType;

	public Campaign(Category category, double discount, int minimumItemCount, DiscountType discountType) {
		super();
		this.category = category;
		this.discount = discount;
		this.minimumItemCount = minimumItemCount;
		this.discountType = discountType;
	}

}
