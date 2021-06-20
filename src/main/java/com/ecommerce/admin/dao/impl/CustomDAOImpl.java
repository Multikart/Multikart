package com.ecommerce.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.dao.CustomDAO;
import com.ecommerce.admin.entity.User;

@Repository
public class CustomDAOImpl implements CustomDAO{

//	@Autowired
//	SessionFactory sessionFactory;
//	@Autowired
//	Session session;
	
	@Override
	public List<User> custom() {
		
		String hql = "SELECT u FROM User u";
		Query query = session.createQuery(hql);
		List<User> userList = query.list();
		return userList;
	}

}
