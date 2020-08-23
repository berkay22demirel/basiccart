package com.berkay22demirel.basiccart.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseEntity {

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

	public Category(long id, String title, Category topCategory) {
		super();
		super.setId(id);
		this.title = title;
		this.topCategory = topCategory;
	}

	public Category() {
		super();
	}

}
