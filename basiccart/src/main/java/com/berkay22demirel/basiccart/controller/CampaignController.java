package com.berkay22demirel.basiccart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.service.ICampaignService;

@RestController
@RequestMapping(value = "/campaign")
public class CampaignController {

	@Autowired
	ICampaignService campaignService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addCampaign(@RequestBody Campaign campaign) {
		campaignService.addCampaign(campaign);
		return new ResponseEntity<>("Campaign is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCampaign(@RequestBody Campaign campaign) {
		campaignService.updateCampaign(campaign);
		return new ResponseEntity<>("Campaign is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteCampaign(@RequestBody Campaign campaign) {
		campaignService.deleteCampaign(campaign);
		return new ResponseEntity<>("Campaign is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll")
	public ResponseEntity<Object> getAllCampaigns() {
		return new ResponseEntity<>(campaignService.getAllCampaigns(), HttpStatus.OK);
	}

}
