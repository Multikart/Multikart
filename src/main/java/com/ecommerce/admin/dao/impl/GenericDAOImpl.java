package com.ecommerce.admin.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Parameterizable;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.admin.dao.GenericDAO;

@Transactional(rollbackOn =  Exception.class)
public class GenericDAOImpl<T, K> implements GenericDAO<T, K> {

	@Autowired(required=true)
	protected SessionFactory sessionFactory;
	
	protected Class<? extends T> clazz;
	
	protected String entityName = "";
	
	 public GenericDAOImpl() {
		Type type = getClass().getGenericSuperclass();
		
		ParameterizedType pType = (ParameterizedType) type;
		clazz = (Class) pType.getActualTypeArguments()[0];
		entityName = clazz.getSimpleName();
	}
	
	@Override
	public List<T> findAll() {
		List<T> entities = new ArrayList<T>();
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM " + entityName, clazz);
		entities = query.getResultList();
		return entities;
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
		
	}

	@Override
	public void delete(K id) {
		Session session = sessionFactory.getCurrentSession();
		// Lấy ra role theo id
		T entity = session.find(clazz, id);

		// Thực hiện xóa role vừa lấy được
		if (entity != null) {
			session.remove(entity);
		}
		
	}

	@Override
	public T findByid(K id) {
		T entity = null;
		Session session = sessionFactory.getCurrentSession();
		entity = session.find(clazz, id);
		return entity;
	}

}
