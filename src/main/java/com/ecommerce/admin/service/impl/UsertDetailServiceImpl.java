package com.ecommerce.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.UserDAO;
import com.ecommerce.admin.dto.LoginDto;
import com.ecommerce.admin.entity.User;

@Service
public class UsertDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserDAO userdao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userdao.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Email is not exist");
		}
		
		String rolename = user.getRole().getName();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(rolename));
		
		LoginDto logindto = new LoginDto(user.getEmail(), user.getPassword(), authorities);
		logindto.setAvatar(user.getAvatar());
		return logindto;
	}

}
