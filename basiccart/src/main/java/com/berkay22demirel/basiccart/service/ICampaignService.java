package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Campaign;

public interface ICampaignService extends IService {

	void addCampaign(Campaign campaign);

	void updateCampaign(Campaign campaign);

	void deleteCampaign(Campaign campaign);

	List<Campaign> getAllCampaigns();

}
