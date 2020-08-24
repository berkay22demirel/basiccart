package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.ICouponDao;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.service.ICouponService;

import lombok.Setter;

@Service
@Setter
public class CouponService implements ICouponService {

	@Autowired
	private ICouponDao couponDao;

	@Override
	public long addCoupon(Coupon coupon) {
		return couponDao.add(coupon);

	}

	@Override
	public long updateCoupon(Coupon coupon) {
		return couponDao.update(coupon);

	}

	@Override
	public long deleteCoupon(long id) {
		return couponDao.delete(id);

	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponDao.findAll();
	}

}
