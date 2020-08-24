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
import com.berkay22demirel.basiccart.util.JsonUtil;

public class CategoryControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addCategory() throws Exception {
		String uri = "/category/add";
		Category category = new Category("meyve");
		String inputJson = JsonUtil.mapToJson(category);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Category is created successfully");
	}

	@Test
	public void updateCategory() throws Exception {
		String uri = "/category/update";
		Category category = new Category(1, "sebze");
		String inputJson = JsonUtil.mapToJson(category);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Category is updated successsfully");
	}

	@Test
	public void deleteCategory() throws Exception {
		String uri = "/category/delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Category is deleted successsfully");
	}

	@Test
	public void getAll() throws Exception {
		String uri = "/category/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Category[] categories = JsonUtil.mapFromJson(content, Category[].class);
		assertTrue(categories.length > 0);
	}

}
