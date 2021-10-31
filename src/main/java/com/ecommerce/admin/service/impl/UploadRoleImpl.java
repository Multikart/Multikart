package com.ecommerce.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.entity.Role;

@Service
public class UploadRoleImpl extends Thread {
	
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public void run() {
//		Role role = new Role();
//		role.setName("Dac");
//		role.setDescription("Desc");
//		role.setValidflag("1");
//		roleDAO.save(role);
		System.out.println("abc");
	}
	
	public void start() {
		
	}


}
