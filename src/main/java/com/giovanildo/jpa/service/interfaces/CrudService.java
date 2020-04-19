package com.giovanildo.jpa.service.interfaces;

import java.util.List;

public interface CrudService<T, K> {
	List<T> all();

	T byId(K id);
	
	T byString(String string);

	T insert(T entity);

	T update(T entity);

	void delete(T entity);

	void deleteById(K id);
}
