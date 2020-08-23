package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.ICampaignDao;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.service.ICampaignService;

@Service
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
	public void deleteCampaign(long id) {
		campaignDao.delete(id);

	}

	@Override
	public List<Campaign> getAllCampaigns() {
		return campaignDao.findAll();
	}

}
