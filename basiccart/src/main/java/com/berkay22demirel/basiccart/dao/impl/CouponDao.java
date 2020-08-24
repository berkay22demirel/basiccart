package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.ICouponDao;
import com.berkay22demirel.basiccart.entity.Coupon;

@Repository
public class CouponDao extends DaoSupport<Coupon> implements ICouponDao {

	public CouponDao() {
		super(Coupon.class);
	}

}
