package com.ecommerce.admin.dto;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.entity.Role;

public class RoleThreadUpload extends Thread{
	
	private Role role;
	private CountDownLatch countDownLatch;
	
	@Autowired
	RoleDAO roleDAO;
	
	public RoleThreadUpload(Role role,CountDownLatch countDownLatch) {
		this.role = role;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread());
		roleDAO.save(role);
		countDownLatch.countDown();
	}
}
