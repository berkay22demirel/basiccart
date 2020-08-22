package com.berkay22demirel.basiccart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
