package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Campaign;

public interface ICampaignService extends IService {

	long addCampaign(Campaign campaign);

	long updateCampaign(Campaign campaign);

	long deleteCampaign(long id);

	List<Campaign> getAllCampaigns();

}
