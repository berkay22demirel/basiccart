package com.berkay22demirel.basiccart.dao;

import java.util.List;

public interface IDao<T> {

	public long add(T object);

	public long update(T object);

	public long delete(long id);

	public T findById(long id);

	public List<T> findAll();

}
