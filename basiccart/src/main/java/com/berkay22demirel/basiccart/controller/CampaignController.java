package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.service.ICampaignService;

@Controller
public class CampaignController {

	@Autowired
	ICampaignService campaignService;

	public void addCampaign(Campaign campaign) {
		campaignService.addCampaign(campaign);
	}

	public void updateCampaign(Campaign campaign) {
		campaignService.updateCampaign(campaign);
	}

	public void deleteCampaign(Campaign campaign) {
		campaignService.deleteCampaign(campaign);
	}

	public List<Campaign> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}

}
