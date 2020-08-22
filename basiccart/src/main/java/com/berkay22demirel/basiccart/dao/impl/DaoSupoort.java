package com.berkay22demirel.basiccart.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.berkay22demirel.basiccart.dao.Database;
import com.berkay22demirel.basiccart.dao.IDao;
import com.berkay22demirel.basiccart.entity.BaseEntity;

public abstract class DaoSupoort<T> implements IDao<T> {

	Class<T> typeParameterClass;

	@Override
	public long add(T object) {
		Map<Long, T> table = Database.getTable(typeParameterClass);
		table.put((long) (table.size() + 1), object);
		return table.size() + 1;
	}

	@Override
	public void update(T object) {
		Map<Long, T> table = Database.getTable(typeParameterClass);
		Long id = ((BaseEntity) object).getId();
		table.replace(id, object);
	}

	@Override
	public void delete(T object) {
		Map<Long, T> table = Database.getTable(typeParameterClass);
		Long id = ((BaseEntity) object).getId();
		table.remove(id);
	}

	@Override
	public T findById(long id) {
		Map<Long, T> table = Database.getTable(typeParameterClass);
		return table.get(id);
	}

	@Override
	public List<T> findAll() {
		Map<Long, T> table = Database.getTable(typeParameterClass);
		List<T> objects = new ArrayList<>();
		for (Map.Entry<Long, T> entry : table.entrySet()) {
			objects.add(entry.getValue());
		}
		return objects;
	}

}
