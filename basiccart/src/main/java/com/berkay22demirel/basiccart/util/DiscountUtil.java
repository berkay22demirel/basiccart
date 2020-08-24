package com.berkay22demirel.basiccart.util;

import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Coupon;

public class DiscountUtil {

	public static double findDiscountAmount(double amount, Campaign campaign) {
		if (DiscountType.RATE.equals(campaign.getDiscountType())) {
			return (amount / 100) * campaign.getDiscount();
		} else if (DiscountType.AMOUNT.equals(campaign.getDiscountType())) {
			return campaign.getDiscount();
		}
		return 0;
	}

	public static double findDiscountAmount(Double totalAmount, double productAmount, Coupon coupon) {
		if (DiscountType.RATE.equals(coupon.getDiscountType())) {
			return (productAmount / 100) * coupon.getDiscount();
		} else if (DiscountType.AMOUNT.equals(coupon.getDiscountType())) {
			double rateOfProductAmountInTotalAmount = (100 * productAmount) / totalAmount;
			return (coupon.getDiscount() / 100) * rateOfProductAmountInTotalAmount;
		}
		return 0;
	}

}
