package com.berkay22demirel.basiccart.util;

public class DiscountUtil {

	public static double findDiscountAmountForRate(double amount, double rate) {
		return (amount / 100) * rate;
	}

}
