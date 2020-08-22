package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.berkay22demirel.basiccart.dao.ICategoryDao;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.ICategoryService;

public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public void addCategory(Category category) {
		categoryDao.add(category);

	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.update(category);

	}

	@Override
	public void deleteCategory(Category category) {
		categoryDao.delete(category);

	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

}
