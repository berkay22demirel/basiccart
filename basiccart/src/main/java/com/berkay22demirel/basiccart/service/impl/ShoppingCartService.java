package com.berkay22demirel.basiccart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IShoppingCartService;
import com.berkay22demirel.basiccart.util.DiscountUtil;

@Service
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	IShoppingCartDao shoppingCartDao;

	@Override
	public void addShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.add(shoppingCart);

	}

	@Override
	public void updateShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.update(shoppingCart);

	}

	@Override
	public void deleteShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDao.delete(shoppingCart);

	}

	@Override
	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartDao.findAll();
	}

	@Override
	public ShoppingCartItem findMatchingItem(List<ShoppingCartItem> shoppingCartItems, Product product) {
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().equals(product)) {
				return shoppingCartItem;
			}
		}
		return null;
	}

	@Override
	public List<Campaign> findApplicableCampaigns(ShoppingCart shoppingCart, List<Campaign> campaigns) {
		List<Campaign> applicableCampaigns = new ArrayList<>();
		for (Campaign campaign : campaigns) {
			Category campaignCategory = campaign.getCategory();
			int numberOfProductsInCategory = 0;
			for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
				if (shoppingCartItem.getProduct().getCategory().equals(campaignCategory)) {
					numberOfProductsInCategory++;
				}
			}
			if (numberOfProductsInCategory > campaign.getMinimumItemCount()) {
				applicableCampaigns.add(campaign);
			}
		}
		return applicableCampaigns;
	}

	@Override
	public void applyDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns) {
		List<Campaign> applicableCampaigns = findApplicableCampaigns(shoppingCart, campaigns);
		double discountRate = 0;
		double discountAmount = 0;
		for (Campaign campaign : applicableCampaigns) {
			if (DiscountType.RATE.equals(campaign.getDiscountType())) {
				discountRate += campaign.getDiscount();
			} else if (DiscountType.AMOUNT.equals(campaign.getDiscountType())) {
				discountAmount += campaign.getDiscount();
			}
		}
		double campaignDiscountAmount = DiscountUtil.findDiscountAmountForRate(shoppingCart.getTotalAmount(),
				discountRate) + discountAmount;
		shoppingCart.setCampaignDiscountAmount(campaignDiscountAmount);
		shoppingCart.setCampaigns(campaigns);
	}

	@Override
	public boolean applyCoupon(ShoppingCart shoppingCart, Coupon coupon) {
		if (shoppingCart.getTotalAmount() > coupon.getMinimumAmount()) {
			double discountAmount = 0;
			if (DiscountType.RATE.equals(coupon.getDiscountType())) {
				discountAmount = DiscountUtil.findDiscountAmountForRate(shoppingCart.getTotalAmount(),
						coupon.getDiscount());
			} else if (DiscountType.AMOUNT.equals(coupon.getDiscountType())) {
				discountAmount = coupon.getDiscount();
			}
			shoppingCart.setCouponDiscountAmount(discountAmount);
			shoppingCart.setCoupon(coupon);
			return true;
		}
		return false;
	}

}
