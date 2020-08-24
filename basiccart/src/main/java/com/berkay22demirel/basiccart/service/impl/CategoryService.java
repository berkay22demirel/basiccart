package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.dao.ICategoryDao;
import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public long addCategory(Category category) {
		return categoryDao.add(category);

	}

	@Override
	public long updateCategory(Category category) {
		return categoryDao.update(category);

	}

	@Override
	public long deleteCategory(long id) {
		return categoryDao.delete(id);

	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

}
