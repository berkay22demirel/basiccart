package com.berkay22demirel.basiccart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkay22demirel.basiccart.entity.Category;
import com.berkay22demirel.basiccart.service.ICategoryService;

@RestController(value = "/category")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
		return new ResponseEntity<>("Category is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
		return new ResponseEntity<>("Category is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteCategory(@RequestBody Category category) {
		categoryService.deleteCategory(category);
		return new ResponseEntity<>("Category is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll")
	public ResponseEntity<Object> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}

}
