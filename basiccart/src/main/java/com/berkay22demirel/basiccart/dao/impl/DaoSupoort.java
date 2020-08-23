package com.berkay22demirel.basiccart.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.berkay22demirel.basiccart.dao.Database;
import com.berkay22demirel.basiccart.dao.IDao;
import com.berkay22demirel.basiccart.entity.BaseEntity;

public abstract class DaoSupoort<T> implements IDao<T> {

	private final Class<T> type;

	public DaoSupoort(Class<T> type) {
		this.type = type;
	}

	@Override
	public long add(T object) {
		Map<Long, Object> table = Database.getTable(type);
		long id = (table.size() + 1);
		((BaseEntity) object).setId(id);
		table.put(id, object);
		return table.size() + 1;
	}

	@Override
	public void update(T object) {
		Map<Long, Object> table = Database.getTable(type);
		Long id = ((BaseEntity) object).getId();
		table.replace(id, object);
	}

	@Override
	public void delete(T object) {
		Map<Long, Object> table = Database.getTable(type);
		Long id = ((BaseEntity) object).getId();
		table.remove(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(long id) {

		Map<Long, Object> table = Database.getTable(type);
		return (T) table.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Map<Long, Object> table = Database.getTable(type);
		List<T> objects = new ArrayList<>();
		for (Map.Entry<Long, Object> entry : table.entrySet()) {
			objects.add((T) entry.getValue());
		}
		return objects;
	}

}
