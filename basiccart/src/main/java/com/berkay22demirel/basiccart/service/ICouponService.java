package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Coupon;

public interface ICouponService extends IService {

	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void deleteCoupon(Coupon coupon);

	List<Coupon> getAllCoupons();

}
