package com.berkay22demirel.basiccart.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
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
		Category category = new Category(2, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		String inputJson = JsonUtil.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(status));
		ShoppingCart shoppingCart = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(),
				ShoppingCart.class);
		assertTrue(shoppingCart.getShoppingCartItems().size() > 0);
	}

	@Test
	public void applyDiscount() throws Exception {
		String uri = "/shoppingcart/applyDiscount";
		Category category1 = new Category(1, "kitap");
		Category category2 = new Category(2, "meyve");
		Campaign campaignRate = new Campaign(1, category1, 15.0, 3, DiscountType.RATE);
		Campaign campaignAmount = new Campaign(2, category2, 1.0, 5, DiscountType.AMOUNT);

		String inputJson = JsonUtil.mapToJson(Arrays.asList(campaignRate, campaignAmount));
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(status));
		ShoppingCart shoppingCart = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(),
				ShoppingCart.class);
		assertTrue(shoppingCart.getCampaigns().size() > 0);
	}

	@Test
	public void applyCoupon() throws Exception {
		String uri = "/shoppingcart/applyCoupon";
		Coupon coupon = new Coupon(1, 0.0, 1.0, DiscountType.AMOUNT);
		String inputJson = JsonUtil.mapToJson(coupon);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(status));
		ShoppingCart shoppingCart = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(),
				ShoppingCart.class);
		assertNotNull(shoppingCart.getCoupon());
	}

	@Test
	public void calculateDeliveryCost() throws Exception {
		String uri = "/shoppingcart/calculateDeliveryCost";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(status));
		ShoppingCart shoppingCart = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(),
				ShoppingCart.class);
		assertTrue(shoppingCart.getDeliveryCost() != 0.0);
	}

}
