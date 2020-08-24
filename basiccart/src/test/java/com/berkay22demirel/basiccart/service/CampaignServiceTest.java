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
import com.berkay22demirel.basiccart.dao.ICampaignDao;
import com.berkay22demirel.basiccart.dao.impl.CampaignDao;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.impl.CampaignService;

public class CampaignServiceTest extends AbstractTest {

	private ICampaignDao campaignDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		campaignDao = mock(CampaignDao.class);
	}

	@Test
	public void addCampaignShouldReturnId() throws Exception {
		Category category = new Category(1, "kitap");
		Campaign campaign = new Campaign(1, category, 15.0, 3, DiscountType.RATE);
		CampaignService campaignService = new CampaignService();
		campaignService.setCampaignDao(campaignDao);

		when(campaignDao.add(campaign)).thenReturn(1l);
		long returnedId = campaignService.addCampaign(campaign);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateCampaignShouldReturnUpdatedId() throws Exception {
		Category category = new Category(2, "meyve");
		Campaign campaign = new Campaign(1, category, 20.0, 4, DiscountType.AMOUNT);
		CampaignService campaignService = new CampaignService();
		campaignService.setCampaignDao(campaignDao);

		when(campaignDao.update(campaign)).thenReturn(1l);
		long returnedId = campaignService.updateCampaign(campaign);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteCampaignShouldReturnDeletedId() throws Exception {
		CampaignService campaignService = new CampaignService();
		campaignService.setCampaignDao(campaignDao);

		when(campaignDao.delete(1)).thenReturn(1l);
		long returnedId = campaignService.deleteCampaign(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllCampaignsShouldReturnCampaigns() throws Exception {
		Category category = new Category(2, "meyve");
		Campaign campaign = new Campaign(1, category, 20.0, 4, DiscountType.AMOUNT);
		CampaignService campaignService = new CampaignService();
		campaignService.setCampaignDao(campaignDao);

		when(campaignDao.findAll()).thenReturn(Arrays.asList(campaign));
		List<Campaign> campaigns = campaignService.getAllCampaigns();

		assertTrue(campaigns.size() > 0);
	}

}
