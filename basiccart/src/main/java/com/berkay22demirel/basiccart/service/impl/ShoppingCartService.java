package com.berkay22demirel.basiccart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IShoppingCartService;
import com.berkay22demirel.basiccart.util.DiscountUtil;

import lombok.Setter;

@Service
@Setter
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	IShoppingCartDao shoppingCartDao;

	@Override
	public long addShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartDao.add(shoppingCart);

	}

	@Override
	public long updateShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartDao.update(shoppingCart);

	}

	@Override
	public long deleteShoppingCart(long id) {
		return shoppingCartDao.delete(id);

	}

	@Override
	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartDao.findAll();
	}

	@Override
	public ShoppingCart addItem(ShoppingCart shoppingCart, Product product, int quantity) {
		ShoppingCartItem matchingItem = findMatchingItem(shoppingCart.getShoppingCartItems(), product);
		if (matchingItem == null) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
			shoppingCart.getShoppingCartItems().add(shoppingCartItem);
		} else {
			matchingItem.setQuantity(matchingItem.getQuantity() + quantity);
		}
		double addedProductAmount = product.getPrice() * quantity;
		shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + addedProductAmount);
		reapplyDiscount(shoppingCart);
		return shoppingCart;
	}

	@Override
	public ShoppingCart applyDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns) {
		double totalDiscountAmount = 0;
		clearOldCampaignAmount(shoppingCart.getShoppingCartItems());
		for (Campaign campaign : campaigns) {
			List<ShoppingCartItem> campaignApplicableShoppingCartItems = findCampaignApplicableShoppingCartItems(
					campaign, shoppingCart.getShoppingCartItems());
			totalDiscountAmount += applyDiscountForShoppingCartItems(campaign, campaignApplicableShoppingCartItems);
		}
		shoppingCart.setCampaignDiscountAmount(totalDiscountAmount);
		shoppingCart.setCampaigns(campaigns);
		return shoppingCart;
	}

	@Override
	public ShoppingCart applyCoupon(ShoppingCart shoppingCart, Coupon coupon) {
		if (shoppingCart.getTotalAmount() >= coupon.getMinimumAmount()) {
			double discountAmount = applyCouponForShoppingCartItems(coupon, shoppingCart.getShoppingCartItems(),
					shoppingCart.getTotalAmount());
			shoppingCart.setCouponDiscountAmount(discountAmount);
			shoppingCart.setCoupon(coupon);
		} else if (shoppingCart.getCoupon() != null) {
			shoppingCart.setCouponDiscountAmount(0.0);
			shoppingCart.setCoupon(null);
		}
		return shoppingCart;
	}

	private void reapplyDiscount(ShoppingCart shoppingCart) {
		if (!CollectionUtils.isEmpty(shoppingCart.getCampaigns())) {
			applyDiscount(shoppingCart, shoppingCart.getCampaigns());
		}

	}

	private ShoppingCartItem findMatchingItem(List<ShoppingCartItem> shoppingCartItems, Product product) {
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().getId() == product.getId()) {
				return shoppingCartItem;
			}
		}
		return null;
	}

	private List<ShoppingCartItem> findCampaignApplicableShoppingCartItems(Campaign campaign,
			List<ShoppingCartItem> shoppingCartItems) {
		List<ShoppingCartItem> campaignApplicableShoppingCartItems = new ArrayList<>();
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			List<Category> productCategories = getAllCategoriesForProduct(shoppingCartItem.getProduct());
			if (isIncludedCampaignCategoryForProductCategories(productCategories, campaign.getCategory())) {
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
				shoppingCartItem.setCampaignDiscountAmountPerProduct(
						shoppingCartItem.getCampaignDiscountAmountPerProduct() + discountAmount);
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

	private List<Category> getAllCategoriesForProduct(Product product) {
		List<Category> productCategories = new ArrayList<>();
		productCategories.add(product.getCategory());
		Category topCategory = product.getCategory().getTopCategory();
		while (true) {
			if (topCategory == null) {
				break;
			}
			productCategories.add(topCategory);
			topCategory = topCategory.getTopCategory();

		}
		return productCategories;
	}

	private boolean isIncludedCampaignCategoryForProductCategories(List<Category> productCategories,
			Category campaignCategory) {
		for (Category category : productCategories) {
			if (campaignCategory.getId() == category.getId()) {
				return true;
			}
		}
		return false;
	}

	private void clearOldCampaignAmount(List<ShoppingCartItem> shoppingCartItems) {
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			shoppingCartItem.setCampaignDiscountAmountPerProduct(0.0);
		}
	}

}
