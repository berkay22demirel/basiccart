package com.berkay22demirel.basiccart.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.berkay22demirel.basiccart.AbstractTest;
import com.berkay22demirel.basiccart.dao.ICategoryDao;
import com.berkay22demirel.basiccart.dao.impl.CategoryDao;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.impl.CategoryService;

public class CategoryServiceTest extends AbstractTest {

	private ICategoryDao categoryDao;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		categoryDao = mock(CategoryDao.class);
	}

	@Test
	public void addCategoryShouldReturnId() throws Exception {
		Category category = new Category(1, "kitap");
		CategoryService categoryService = new CategoryService();
		categoryService.setCategoryDao(categoryDao);

		when(categoryDao.add(category)).thenReturn(1l);
		long returnedId = categoryService.addCategory(category);

		assertTrue(returnedId > 0);
	}

	@Test
	public void updateCategoryShouldReturnUpdatedId() throws Exception {
		Category category = new Category(1, "meyve");
		CategoryService categoryService = new CategoryService();
		categoryService.setCategoryDao(categoryDao);

		when(categoryDao.update(category)).thenReturn(1l);
		long returnedId = categoryService.updateCategory(category);

		assertTrue(returnedId > 0);
	}

	@Test
	public void deleteCategoryShouldReturnDeletedId() throws Exception {
		CategoryService categoryService = new CategoryService();
		categoryService.setCategoryDao(categoryDao);

		when(categoryDao.delete(1)).thenReturn(1l);
		long returnedId = categoryService.deleteCategory(1);

		assertTrue(returnedId > 0);
	}

	@Test
	public void getAllCategorysShouldReturnCategorys() throws Exception {
		Category category = new Category(1, "meyve");
		CategoryService categoryService = new CategoryService();
		categoryService.setCategoryDao(categoryDao);

		when(categoryDao.findAll()).thenReturn(Arrays.asList(category));
		List<Category> categorys = categoryService.getAllCategories();

		assertTrue(categorys.size() > 0);
	}

}
