package com.berkay22demirel.basiccart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.berkay22demirel.basiccart.dao.ICampaignDao;
import com.berkay22demirel.basiccart.entity.Campaign;

public class CampaignService implements ICampaignService {

	@Autowired
	private ICampaignDao campaignDao;

	@Override
	public void addCampaign(Campaign campaign) {
		campaignDao.add(campaign);

	}

	@Override
	public void updateCampaign(Campaign campaign) {
		campaignDao.update(campaign);

	}

	@Override
	public void deleteCampaign(Campaign campaign) {
		campaignDao.delete(campaign);

	}

	@Override
	public List<Campaign> getAllCampaigns() {
		return campaignDao.findAll();
	}

}
