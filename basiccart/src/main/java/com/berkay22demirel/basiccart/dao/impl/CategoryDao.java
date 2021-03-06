package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.ICategoryDao;
import com.berkay22demirel.basiccart.entity.Category;

@Repository
public class CategoryDao extends DaoSupport<Category> implements ICategoryDao {

	public CategoryDao() {
		super(Category.class);
	}

}
