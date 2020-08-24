package com.berkay22demirel.basiccart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.entity.Campaign;
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
	public void deleteShoppingCart(long id) {
		shoppingCartDao.delete(id);

	}

	@Override
	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartDao.findAll();
	}

	@Override
	public void addItem(ShoppingCart shoppingCart, Product product, int quantity) {
		ShoppingCartItem matchingItem = findMatchingItem(shoppingCart.getShoppingCartItems(), product);
		if (matchingItem == null) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
			shoppingCart.getShoppingCartItems().add(shoppingCartItem);
		} else {
			matchingItem.setQuantity(matchingItem.getQuantity() + quantity);
		}
		double addedProductAmount = product.getPrice() * quantity;
		shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + addedProductAmount);
	}

	@Override
	public void applyDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns) {
		double totalDiscountAmount = 0;
		for (Campaign campaign : campaigns) {
			List<ShoppingCartItem> campaignApplicableShoppingCartItems = findCampaignApplicableShoppingCartItems(
					campaign, shoppingCart.getShoppingCartItems());
			totalDiscountAmount += applyDiscountForShoppingCartItems(campaign, campaignApplicableShoppingCartItems);
		}
		shoppingCart.setCampaignDiscountAmount(totalDiscountAmount);
		shoppingCart.setCampaigns(campaigns);
	}

	@Override
	public boolean applyCoupon(ShoppingCart shoppingCart, Coupon coupon) {
		if (shoppingCart.getTotalAmount() >= coupon.getMinimumAmount()) {
			double discountAmount = applyCouponForShoppingCartItems(coupon, shoppingCart.getShoppingCartItems(),
					shoppingCart.getTotalAmount());
			shoppingCart.setCouponDiscountAmount(discountAmount);
			shoppingCart.setCoupon(coupon);
			return true;
		}
		return false;
	}

	private ShoppingCartItem findMatchingItem(List<ShoppingCartItem> shoppingCartItems, Product product) {
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().equals(product)) {
				return shoppingCartItem;
			}
		}
		return null;
	}

	private List<ShoppingCartItem> findCampaignApplicableShoppingCartItems(Campaign campaign,
			List<ShoppingCartItem> shoppingCartItems) {
		List<ShoppingCartItem> campaignApplicableShoppingCartItems = new ArrayList<>();
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (campaign.getCategory().equals(shoppingCartItem.getProduct().getCategory())) {
				campaignApplicableShoppingCartItems.add(shoppingCartItem);
			}
		}
		return campaignApplicableShoppingCartItems;
	}

	private double applyDiscountForShoppingCartItems(Campaign campaign, List<ShoppingCartItem> shoppingCartItems) {
		double totalDiscountAmount = 0;
		int numberOfProducts = findNumberOfProducts(shoppingCartItems);
		if (numberOfProducts >= campaign.getMinimumItemCount()) {
			for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
				double discountAmount = DiscountUtil.findDiscountAmount(shoppingCartItem.getProduct().getPrice(),
						campaign);
				shoppingCartItem.setCampaignDiscountAmountPerProduct(discountAmount);
				totalDiscountAmount += (discountAmount * shoppingCartItem.getQuantity());
			}
		}
		return totalDiscountAmount;
	}

	private double applyCouponForShoppingCartItems(Coupon coupon, List<ShoppingCartItem> shoppingCartItems,
			double totalAmount) {
		double totalDiscountAmount = 0;
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			double discountAmount = DiscountUtil.findDiscountAmount(totalAmount,
					shoppingCartItem.getProduct().getPrice(), coupon);
			shoppingCartItem.setCouponDiscountAmountPerProduct(discountAmount);
			totalDiscountAmount += (discountAmount * shoppingCartItem.getQuantity());
		}
		return totalDiscountAmount;
	}

	private int findNumberOfProducts(List<ShoppingCartItem> shoppingCartItems) {
		int numberOfProducts = 0;
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			numberOfProducts += shoppingCartItem.getQuantity();
		}
		return numberOfProducts;
	}

}
