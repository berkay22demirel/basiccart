package com.berkay22demirel.basiccart.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.util.JsonUtil;

public class CouponControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addCoupon() throws Exception {
		String uri = "/coupon/add";
		Coupon coupon = new Coupon(100, 25, DiscountType.RATE);
		String inputJson = JsonUtil.mapToJson(coupon);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Coupon is created successfully");
	}

	@Test
	public void updateCoupon() throws Exception {
		String uri = "/coupon/update";
		Coupon coupon = new Coupon(1, 100, 15, DiscountType.RATE);
		String inputJson = JsonUtil.mapToJson(coupon);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Coupon is updated successsfully");
	}

	@Test
	public void deleteCoupon() throws Exception {
		String uri = "/coupon/delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Coupon is deleted successsfully");
	}

	@Test
	public void getAll() throws Exception {
		String uri = "/coupon/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Coupon[] coupons = JsonUtil.mapFromJson(content, Coupon[].class);
		assertTrue(coupons.length > 0);
	}

}
