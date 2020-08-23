package com.berkay22demirel.basiccart.service;

import java.util.List;

import com.berkay22demirel.basiccart.entity.Category;

public interface ICategoryService extends IService {

	void addCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(long id);

	List<Category> getAllCategories();

}
