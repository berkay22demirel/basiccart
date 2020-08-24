package com.berkay22demirel.basiccart.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.util.JsonUtil;

public class ShoppingCartControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addItem() throws Exception {
		String uri = "/shoppingcart/add/3";
		Category category = new Category(1, "meyve");
		Product product = new Product("elma", 5.0, category);
		String inputJson = JsonUtil.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Item is added successfully");
	}

	@Test
	public void applyDiscount() throws Exception {
		String uri = "/shoppingcart/applyDiscount";
		Category category1 = new Category("meyve");
		Campaign campaign1 = new Campaign(1, category1, 5.0, 5, DiscountType.AMOUNT);
		Category category2 = new Category("sebze");
		Campaign campaign2 = new Campaign(2, category2, 10.0, 3, DiscountType.RATE);

		String inputJson = JsonUtil.mapToJson(Arrays.asList(campaign1, campaign2));
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Discount is applied successfully");
	}

	@Test
	public void applyCoupon() throws Exception {
		String uri = "/shoppingcart/applyCoupon";
		Coupon coupon = new Coupon(1, 100, 15.0, DiscountType.AMOUNT);
		String inputJson = JsonUtil.mapToJson(coupon);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Coupon is applied successfully");
	}

	@Test
	public void calculateDeliveryCost() throws Exception {
		String uri = "/shoppingcart/calculateDeliveryCost";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Delivery Cost is calculated successfully");
	}

}
