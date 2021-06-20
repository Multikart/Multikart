package com.ecommerce.admin.dao;

import java.util.List;

public interface GenericDAO<T, K> {
	public List<T> findAll();
	public void saveOrUpdate(T model);
	public void delete(K id);
	public T findByid(K id);
	
}
