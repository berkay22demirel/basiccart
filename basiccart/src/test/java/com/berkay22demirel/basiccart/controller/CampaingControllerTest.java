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
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.util.JsonUtil;

public class CampaingControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addCampaing() throws Exception {
		String uri = "/campaign/add";
		Category category = new Category("meyve");
		Campaign campaign = new Campaign(category, 5.0, 5, DiscountType.AMOUNT);
		String inputJson = JsonUtil.mapToJson(campaign);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Campaign is created successfully");
	}

	@Test
	public void updateCampaign() throws Exception {
		String uri = "/campaign/update";
		Category category = new Category("meyve");
		Campaign campaign = new Campaign(category, 15.0, 3, DiscountType.RATE);
		campaign.setId(1);
		String inputJson = JsonUtil.mapToJson(campaign);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Campaign is updated successsfully");
	}

	@Test
	public void deleteCampaign() throws Exception {
		String uri = "/campaign/delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Campaign is deleted successsfully");
	}

	@Test
	public void getAll() throws Exception {
		String uri = "/campaign/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Campaign[] campaigns = JsonUtil.mapFromJson(content, Campaign[].class);
		assertTrue(campaigns.length > 0);
	}

}
