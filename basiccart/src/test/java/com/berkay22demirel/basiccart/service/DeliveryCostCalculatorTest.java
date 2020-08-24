package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.impl.DeliveryCostCalculator;

public class DeliveryCostCalculatorTest extends AbstractTest {

	Product product1;
	Product product2;
	Product product3;
	Category category1;
	Category category2;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		category1 = new Category(1, "kitap");
		category2 = new Category(2, "meyve");
		product1 = new Product(1, "elma", 5.0, category2);
		product2 = new Product(2, "Nutuk", 20.0, category1);
		product3 = new Product(3, "Armut", 3.0, category2);

	}

	@Test
	public void calculateForShouldIncreaseDeliveryCostWhenHaveOneCategoryAndOneProduct() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartItems(Arrays.asList(new ShoppingCartItem(product1, 3)));
		IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		deliveryCostCalculator.calculateFor(shoppingCart);
		assertEquals(shoppingCart.getDeliveryCost(), 4.9, 0);
	}

	@Test
	public void calculateForShouldIncreaseDeliveryCostWhenHaveOneCategoryAndManyProduct() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartItems(
				Arrays.asList(new ShoppingCartItem(product1, 3), new ShoppingCartItem(product3, 2)));
		IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		deliveryCostCalculator.calculateFor(shoppingCart);
		assertEquals(shoppingCart.getDeliveryCost(), 5.9, 0);
	}

	@Test
	public void calculateForShouldIncreaseDeliveryCostWhenHaveManyCategoryAndManyProduct() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartItems(
				Arrays.asList(new ShoppingCartItem(product1, 3), new ShoppingCartItem(product2, 4)));
		IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		deliveryCostCalculator.calculateFor(shoppingCart);
		assertEquals(shoppingCart.getDeliveryCost(), 6.9, 0);
	}

}
