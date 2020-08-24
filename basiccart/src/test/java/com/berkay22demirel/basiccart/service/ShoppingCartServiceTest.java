package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.impl.ShoppingCartService;

public class ShoppingCartServiceTest extends AbstractTest {

	Product product1;
	Product product2;
	Product product3;
	Product product4;
	Product product5;
	Category category1;
	Category category2;
	Category category3;
	Campaign campaignRate;
	Campaign campaignAmount;
	Coupon couponRate;
	Coupon couponAmount;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		category1 = new Category(1, "meyve");
		category2 = new Category(2, "yiyecek");
		category3 = new Category(3, "sebze", category2);
		product1 = new Product(1, "elma", 5.0, category1);
		product2 = new Product(2, "armut", 3.0, category1);
		product3 = new Product(3, "muz", 9.0, category1);
		product4 = new Product(4, "patates", 5.0, category3);
		product5 = new Product(5, "havuç", 6.0, category3);
		campaignRate = new Campaign(1, category1, 15.0, 5, DiscountType.RATE);
		campaignAmount = new Campaign(2, category3, 2.0, 3, DiscountType.AMOUNT);
		couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);

	}

	@Test
	public void addItemShouldIncreaseTotalAmount() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		assertEquals(shoppingCart.getTotalAmount(), 15.0, 0);
	}

	@Test
	public void addItemShouldIncreaseShoppingCartItemsSize() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		assertEquals(shoppingCart.getShoppingCartItems().size(), 1);
	}

	@Test
	public void addItemShouldIncreaseShoppingCartItemQuantity() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
			if (product1.equals(shoppingCartItem.getProduct())) {
				assertEquals(shoppingCartItem.getQuantity(), 3);
			}
		}
	}

	@Test
	public void applyDiscountShouldAppliedDiscountWhenProductCountBiggerThanMinimumItemCount() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 2);
		shoppingCartService.addItem(shoppingCart, product2, 5);
		shoppingCartService.addItem(shoppingCart, product4, 3);
		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));
		assertEquals(shoppingCart.getCampaignDiscountAmount(), 9.75, 0);
	}

	@Test
	public void applyDiscountShouldNotAppliedDiscountWhenProductCountSmallerThanMinimumItemCount() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 1);
		shoppingCartService.addItem(shoppingCart, product2, 1);
		shoppingCartService.addItem(shoppingCart, product4, 2);
		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));
		assertEquals(shoppingCart.getCampaignDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyDiscountShouldAppliedDiscountForProductWhenProductCountBiggerThanMinimumItemCount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 5);
		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate));
		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCampaignDiscountAmountPerProduct(), 0.75, 0);
	}

	@Test
	public void applyDiscountShouldNotAppliedDiscountForProductWhenProductCountSmallerThanMinimumItemCount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 4);
		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate));
		assertEquals(shoppingCart.getCampaignDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyDiscountShouldIncreaseCampaignsSize() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 2);
		shoppingCartService.addItem(shoppingCart, product2, 5);
		shoppingCartService.addItem(shoppingCart, product4, 3);
		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));
		assertEquals(shoppingCart.getCampaigns().size(), 2);
	}

	@Test
	public void applyCouponShouldAppliedCouponForDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 4);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertEquals(shoppingCart.getCouponDiscountAmount(), 1.0, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 20);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertEquals(shoppingCart.getCouponDiscountAmount(), 10.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertEquals(shoppingCart.getCouponDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 19);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertEquals(shoppingCart.getCouponDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForProductAndDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 4);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.25, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForProductAndDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 20);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.5, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForProductAndDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForProductAndDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 19);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldAddedCouponForDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 4);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertNotNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldAddedCouponForDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();

		// FIXME

		shoppingCartService.addItem(shoppingCart, product1, 20);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertNotNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldNotAddedCouponForDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 3);
		shoppingCartService.applyCoupon(shoppingCart, couponRate);
		assertNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldNotAddedCouponForDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.addItem(shoppingCart, product1, 19);
		shoppingCartService.applyCoupon(shoppingCart, couponAmount);
		assertNull(shoppingCart.getCoupon());
	}

}
