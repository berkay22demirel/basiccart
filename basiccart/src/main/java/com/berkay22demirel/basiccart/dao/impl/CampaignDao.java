package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.ICampaignDao;
import com.berkay22demirel.basiccart.entity.Campaign;

@Repository
public class CampaignDao extends DaoSupport<Campaign> implements ICampaignDao {

	public CampaignDao() {
		super(Campaign.class);
	}

}
