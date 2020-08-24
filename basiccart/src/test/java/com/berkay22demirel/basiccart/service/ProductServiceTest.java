package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.dao.IProductDao;
import com.berkay22demirel.basiccart.dao.impl.ProductDao;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.service.impl.ProductService;

public class ProductServiceTest extends AbstractTest {

	private IProductDao productDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		productDao = mock(ProductDao.class);
	}

	@Test
	public void addProductShouldReturnId() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "elma", 5.0, category);
		ProductService productService = new ProductService();
		productService.setProductDao(productDao);

		when(productDao.add(product)).thenReturn(1l);
		long returnedId = productService.addProduct(product);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateProductShouldReturnUpdatedId() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "armut", 6.0, category);
		ProductService productService = new ProductService();
		productService.setProductDao(productDao);

		when(productDao.update(product)).thenReturn(1l);
		long returnedId = productService.updateProduct(product);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteProductShouldReturnDeletedId() throws Exception {
		ProductService productService = new ProductService();
		productService.setProductDao(productDao);

		when(productDao.delete(1)).thenReturn(1l);
		long returnedId = productService.deleteProduct(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllProductsShouldReturnProducts() throws Exception {
		Category category = new Category(1, "meyve");
		Product product = new Product(1, "armut", 6.0, category);
		ProductService productService = new ProductService();
		productService.setProductDao(productDao);

		when(productDao.findAll()).thenReturn(Arrays.asList(product));
		List<Product> products = productService.getAllProducts();

		assertTrue(products.size() > 0);
	}

}
