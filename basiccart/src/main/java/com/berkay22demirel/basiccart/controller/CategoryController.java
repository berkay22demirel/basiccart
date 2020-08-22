package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.ICategoryService;

@Controller
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	public void addCategory(Category category) {
		categoryService.addCategory(category);
	}

	public void updateCategory(Category category) {
		categoryService.updateCategory(category);
	}

	public void deleteCategory(Category category) {
		categoryService.deleteCategory(category);
	}

	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

}
