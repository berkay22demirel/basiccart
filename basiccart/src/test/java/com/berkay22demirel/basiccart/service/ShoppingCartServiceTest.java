package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.constant.DiscountType;
import com.berkay22demirel.basiccart.dao.IShoppingCartDao;
import com.berkay22demirel.basiccart.dao.impl.ShoppingCartDao;
import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.impl.ShoppingCartService;

public class ShoppingCartServiceTest extends AbstractTest {

	private IShoppingCartDao shoppingCartDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		shoppingCartDao = mock(ShoppingCartDao.class);

	}

	@Test
	public void addShoppingCartShouldReturnId() throws Exception {
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCartService.setShoppingCartDao(shoppingCartDao);

		when(shoppingCartDao.add(shoppingCart)).thenReturn(1l);
		long returnedId = shoppingCartService.addShoppingCart(shoppingCart);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateShoppingCartShouldReturnUpdatedId() throws Exception {
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.setShoppingCartDao(shoppingCartDao);
		ShoppingCart shoppingCart = new ShoppingCart();

		when(shoppingCartDao.update(shoppingCart)).thenReturn(1l);
		long returnedId = shoppingCartService.updateShoppingCart(shoppingCart);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteShoppingCartShouldReturnDeletedId() throws Exception {
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.setShoppingCartDao(shoppingCartDao);

		when(shoppingCartDao.delete(1)).thenReturn(1l);
		long returnedId = shoppingCartService.deleteShoppingCart(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllShoppingCartsShouldReturnShoppingCarts() throws Exception {
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		shoppingCartService.setShoppingCartDao(shoppingCartDao);
		ShoppingCart shoppingCart = new ShoppingCart();

		when(shoppingCartDao.findAll()).thenReturn(Arrays.asList(shoppingCart));
		List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();

		assertTrue(shoppingCarts.size() > 0);
	}

	@Test
	public void addItemShouldIncreaseTotalAmount() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);

		shoppingCartService.addItem(shoppingCart, product, 3);

		assertEquals(shoppingCart.getTotalAmount(), 15.0, 0);
	}

	@Test
	public void addItemShouldIncreaseShoppingCartItemsSize() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);

		shoppingCartService.addItem(shoppingCart, product, 3);

		assertEquals(shoppingCart.getShoppingCartItems().size(), 1);
	}

	@Test
	public void addItemShouldIncreaseShoppingCartItemQuantity() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);

		shoppingCartService.addItem(shoppingCart, product, 3);

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getQuantity(), 3);
	}

	@Test
	public void applyDiscountShouldAppliedDiscountWhenProductCountBiggerThanMinimumItemCount() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category1 = new Category(1, "meyve");
		Category category2 = new Category(2, "yiyecek");
		Category category3 = new Category(3, "sebze", category2);
		Product product1 = new Product(1, "elma", 5.0, category1);
		Product product2 = new Product(2, "armut", 3.0, category1);
		Product product3 = new Product(4, "patates", 5.0, category3);
		Campaign campaignRate = new Campaign(1, category1, 15.0, 5, DiscountType.RATE);
		Campaign campaignAmount = new Campaign(2, category3, 2.0, 3, DiscountType.AMOUNT);
		ShoppingCartItem shoppingCartItem1 = new ShoppingCartItem(1, product1, 2);
		ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem(2, product2, 5);
		ShoppingCartItem shoppingCartItem3 = new ShoppingCartItem(3, product3, 3);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem1, shoppingCartItem2, shoppingCartItem3));
		shoppingCart.setTotalAmount(52.0);

		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));

		assertEquals(shoppingCart.getCampaignDiscountAmount(), 9.75, 0);
	}

	@Test
	public void applyDiscountShouldNotAppliedDiscountWhenProductCountSmallerThanMinimumItemCount() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category1 = new Category(1, "meyve");
		Category category2 = new Category(2, "yiyecek");
		Category category3 = new Category(3, "sebze", category2);
		Product product1 = new Product(1, "elma", 5.0, category1);
		Product product2 = new Product(2, "armut", 3.0, category1);
		Product product3 = new Product(4, "patates", 5.0, category3);
		Campaign campaignRate = new Campaign(1, category1, 15.0, 5, DiscountType.RATE);
		Campaign campaignAmount = new Campaign(2, category3, 2.0, 3, DiscountType.AMOUNT);
		ShoppingCartItem shoppingCartItem1 = new ShoppingCartItem(1, product1, 1);
		ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem(2, product2, 1);
		ShoppingCartItem shoppingCartItem3 = new ShoppingCartItem(3, product3, 2);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem1, shoppingCartItem2, shoppingCartItem3));
		shoppingCart.setTotalAmount(18.0);

		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));

		assertEquals(shoppingCart.getCampaignDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyDiscountShouldAppliedDiscountForProductWhenProductCountBiggerThanMinimumItemCount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 5);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(25.0);
		Campaign campaignRate = new Campaign(1, category, 15.0, 5, DiscountType.RATE);

		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate));

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCampaignDiscountAmountPerProduct(), 0.75, 0);
	}

	@Test
	public void applyDiscountShouldNotAppliedDiscountForProductWhenProductCountSmallerThanMinimumItemCount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 4);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(20.0);
		Campaign campaignRate = new Campaign(1, category, 15.0, 5, DiscountType.RATE);

		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate));

		assertEquals(shoppingCart.getCampaignDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyDiscountShouldIncreaseCampaignsSize() throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Category category1 = new Category(1, "meyve");
		Category category2 = new Category(2, "yiyecek");
		Category category3 = new Category(3, "sebze", category2);
		Campaign campaignRate = new Campaign(1, category1, 15.0, 5, DiscountType.RATE);
		Campaign campaignAmount = new Campaign(2, category3, 2.0, 3, DiscountType.AMOUNT);

		shoppingCartService.applyDiscount(shoppingCart, Arrays.asList(campaignRate, campaignAmount));

		assertEquals(shoppingCart.getCampaigns().size(), 2);
	}

	@Test
	public void applyCouponShouldAppliedCouponForDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 4);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(20.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertEquals(shoppingCart.getCouponDiscountAmount(), 1.0, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 20);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(100.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertEquals(shoppingCart.getCouponDiscountAmount(), 10.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 3);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(15.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertEquals(shoppingCart.getCouponDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 19);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(95.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertEquals(shoppingCart.getCouponDiscountAmount(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForProductAndDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 4);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(20.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.25, 0);
	}

	@Test
	public void applyCouponShouldAppliedCouponForProductAndDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 20);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(100.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.5, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForProductAndDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 3);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(15.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldNotAppliedCouponForProductAndDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 19);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(95.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertEquals(shoppingCart.getShoppingCartItems().get(0).getCouponDiscountAmountPerProduct(), 0.0, 0);
	}

	@Test
	public void applyCouponShouldAddedCouponForDiscountTypeRateWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 4);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(20.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertNotNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldAddedCouponForDiscountTypeAmountWhenTotalAmountBiggerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 20);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(100.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertNotNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldNotAddedCouponForDiscountTypeRateWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponRate = new Coupon(1, 20.0, 5.0, DiscountType.RATE);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 3);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(15.0);

		shoppingCartService.applyCoupon(shoppingCart, couponRate);

		assertNull(shoppingCart.getCoupon());
	}

	@Test
	public void applyCouponShouldNotAddedCouponForDiscountTypeAmountWhenTotalAmountSmallerThanMinimumAmount()
			throws Exception {
		IShoppingCartService shoppingCartService = new ShoppingCartService();
		ShoppingCart shoppingCart = new ShoppingCart();
		Coupon couponAmount = new Coupon(2, 100.0, 10.0, DiscountType.AMOUNT);
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 19);
		shoppingCart.setShoppingCartItems(Arrays.asList(shoppingCartItem));
		shoppingCart.setTotalAmount(95.0);

		shoppingCartService.applyCoupon(shoppingCart, couponAmount);

		assertNull(shoppingCart.getCoupon());
	}

}
