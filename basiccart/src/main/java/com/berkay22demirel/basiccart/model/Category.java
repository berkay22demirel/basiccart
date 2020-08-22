package com.berkay22demirel.basiccart.model;

public class Category extends BaseModel {

	private String title;
	private Category topCategory;

	public Category(String title) {
		super();
		this.title = title;
	}

	public Category(String title, Category topCategory) {
		super();
		this.title = title;
		this.topCategory = topCategory;
	}

}
