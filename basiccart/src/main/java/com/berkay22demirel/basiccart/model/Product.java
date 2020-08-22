package com.berkay22demirel.basiccart.model;

public class Product extends BaseModel {

	private String name;
	private double price;
	private Category category;

	public Product(String name, double price, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

}