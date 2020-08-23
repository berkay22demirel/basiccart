package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.ICouponDao;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.service.ICouponService;

@Service
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
	public void deleteCoupon(long id) {
		couponDao.delete(id);

	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponDao.findAll();
	}

}
