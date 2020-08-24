package com.berkay22demirel.basiccart.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.util.JsonUtil;

public class ProductControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addProduct() throws Exception {
		String uri = "/product/add";
		Category category = new Category("meyve");
		Product product = new Product("elma", 5.0, category);
		String inputJson = JsonUtil.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is created successfully");
	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/product/update";
		Category category = new Category("meyve");
		Product product = new Product(1, "elma", 7.0, category);
		String inputJson = JsonUtil.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is updated successsfully");
	}

	@Test
	public void deleteProduct() throws Exception {
		String uri = "/product/delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is deleted successsfully");
	}

	@Test
	public void getAll() throws Exception {
		String uri = "/product/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] products = JsonUtil.mapFromJson(content, Product[].class);
		assertTrue(products.length > 0);
	}

}
