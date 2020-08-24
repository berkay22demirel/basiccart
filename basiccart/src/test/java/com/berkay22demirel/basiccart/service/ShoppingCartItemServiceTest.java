package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.dao.IShoppingCartItemDao;
import com.berkay22demirel.basiccart.dao.impl.ShoppingCartItemDao;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.impl.ShoppingCartItemService;

public class ShoppingCartItemServiceTest extends AbstractTest {

	private IShoppingCartItemDao shoppingCartItemDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		shoppingCartItemDao = mock(ShoppingCartItemDao.class);
	}

	@Test
	public void addShoppingCartItemShouldReturnId() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 3);
		ShoppingCartItemService shoppingCartItemService = new ShoppingCartItemService();
		shoppingCartItemService.setShoppingCartItemDao(shoppingCartItemDao);

		when(shoppingCartItemDao.add(shoppingCartItem)).thenReturn(1l);
		long returnedId = shoppingCartItemService.addShoppingCartItem(shoppingCartItem);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateShoppingCartItemShouldReturnUpdatedId() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 5);
		ShoppingCartItemService shoppingCartItemService = new ShoppingCartItemService();
		shoppingCartItemService.setShoppingCartItemDao(shoppingCartItemDao);

		when(shoppingCartItemDao.update(shoppingCartItem)).thenReturn(1l);
		long returnedId = shoppingCartItemService.updateShoppingCartItem(shoppingCartItem);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteShoppingCartItemShouldReturnDeletedId() throws Exception {
		ShoppingCartItemService shoppingCartItemService = new ShoppingCartItemService();
		shoppingCartItemService.setShoppingCartItemDao(shoppingCartItemDao);

		when(shoppingCartItemDao.delete(1)).thenReturn(1l);
		long returnedId = shoppingCartItemService.deleteShoppingCartItem(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllShoppingCartItemsShouldReturnShoppingCartItems() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(1, product, 5);
		ShoppingCartItemService shoppingCartItemService = new ShoppingCartItemService();
		shoppingCartItemService.setShoppingCartItemDao(shoppingCartItemDao);

		when(shoppingCartItemDao.findAll()).thenReturn(Arrays.asList(shoppingCartItem));
		List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllShoppingCartItems();

		assertTrue(shoppingCartItems.size() > 0);
	}

}
