package com.berkay22demirel.basiccart.model;

import com.berkay22demirel.basiccart.constant.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coupon extends BaseModel {

	private double minimumAmount;
	private double discount;
	private DiscountType discountType;

	public Coupon(double minimumAmount, double discount, DiscountType discountType) {
		super();
		this.minimumAmount = minimumAmount;
		this.discount = discount;
		this.discountType = discountType;
	}

}
