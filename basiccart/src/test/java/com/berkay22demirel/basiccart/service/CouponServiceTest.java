package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.dao.ICouponDao;
import com.berkay22demirel.basiccart.dao.impl.CouponDao;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.service.impl.CouponService;

public class CouponServiceTest extends AbstractTest {

	private ICouponDao couponDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		couponDao = mock(CouponDao.class);
	}

	@Test
	public void addCouponShouldReturnId() throws Exception {
		Coupon coupon = new Coupon(1, 50.0, 5.0, DiscountType.AMOUNT);
		CouponService couponService = new CouponService();
		couponService.setCouponDao(couponDao);

		when(couponDao.add(coupon)).thenReturn(1l);
		long returnedId = couponService.addCoupon(coupon);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateCouponShouldReturnUpdatedId() throws Exception {
		Coupon coupon = new Coupon(1, 50.0, 5.0, DiscountType.AMOUNT);
		CouponService couponService = new CouponService();
		couponService.setCouponDao(couponDao);

		when(couponDao.update(coupon)).thenReturn(1l);
		long returnedId = couponService.updateCoupon(coupon);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteCouponShouldReturnDeletedId() throws Exception {
		CouponService couponService = new CouponService();
		couponService.setCouponDao(couponDao);

		when(couponDao.delete(1)).thenReturn(1l);
		long returnedId = couponService.deleteCoupon(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllCouponsShouldReturnCoupons() throws Exception {
		Coupon coupon = new Coupon(1, 50.0, 5.0, DiscountType.AMOUNT);
		CouponService couponService = new CouponService();
		couponService.setCouponDao(couponDao);

		when(couponDao.findAll()).thenReturn(Arrays.asList(coupon));
		List<Coupon> coupons = couponService.getAllCoupons();

		assertTrue(coupons.size() > 0);
	}

}
