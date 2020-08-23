package com.berkay22demirel.basiccart.entity;

import com.berkay22demirel.basiccart.constant.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Campaign extends BaseEntity {

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

	public Campaign(long id) {
		super.setId(id);
	}

	public Campaign(long id, Category category, double discount, int minimumItemCount, DiscountType discountType) {
		super();
		super.setId(id);
		this.category = category;
		this.discount = discount;
		this.minimumItemCount = minimumItemCount;
		this.discountType = discountType;
	}

	public Campaign() {
		super();
	}

}
