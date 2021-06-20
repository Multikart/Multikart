package com.ecommerce.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;

public interface UserService {
	List<UserDto> findAll();
	UserDto findById(int id);
	void save(UserDto dto);
	void update(UserDto dto);
	void delete(int id);
	public Page<User> findAllPaging(Pageable pageAble);
	public List<User> custom();
}
