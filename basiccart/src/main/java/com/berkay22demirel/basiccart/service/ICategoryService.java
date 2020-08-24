package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Category;

public interface ICategoryService extends IService {

	long addCategory(Category category);

	long updateCategory(Category category);

	long deleteCategory(long id);

	List<Category> getAllCategories();

}
