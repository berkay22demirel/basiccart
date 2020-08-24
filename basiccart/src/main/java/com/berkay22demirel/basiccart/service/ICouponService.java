package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Coupon;

public interface ICouponService extends IService {

	long addCoupon(Coupon coupon);

	long updateCoupon(Coupon coupon);

	long deleteCoupon(long id);

	List<Coupon> getAllCoupons();

}
