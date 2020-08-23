package com.berkay22demirel.basiccart.dao;

import java.util.List;

public interface IDao<T> {

	public long add(T object);

	public void update(T object);

	public void delete(long id);

	public T findById(long id);

	public List<T> findAll();

}
