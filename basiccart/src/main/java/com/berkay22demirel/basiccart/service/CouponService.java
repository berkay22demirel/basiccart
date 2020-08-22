package com.berkay22demirel.basiccart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.berkay22demirel.basiccart.dao.ICouponDao;
import com.berkay22demirel.basiccart.entity.Coupon;

public class CouponService implements ICouponService {

	@Autowired
	private ICouponDao couponDao;

	@Override
	public void addCoupon(Coupon coupon) {
		couponDao.add(coupon);

	}

	@Override
	public void updateCoupon(Coupon coupon) {
		couponDao.update(coupon);

	}

	@Override
	public void deleteCoupon(Coupon coupon) {
		couponDao.delete(coupon);

	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponDao.findAll();
	}

}
