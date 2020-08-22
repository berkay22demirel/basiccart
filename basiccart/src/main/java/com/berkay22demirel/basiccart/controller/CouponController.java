package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.service.ICouponService;

@Controller
public class CouponController {

	@Autowired
	ICouponService couponService;

	public void addCoupon(Coupon coupon) {
		couponService.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponService.updateCoupon(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		couponService.deleteCoupon(coupon);
	}

	public List<Coupon> getAllCoupons() {
		return couponService.getAllCoupons();
	}

}
