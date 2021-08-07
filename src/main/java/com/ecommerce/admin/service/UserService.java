package com.ecommerce.admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;

public interface UserService {
	List<UserDto> findAll();
	UserDto findById(int id);
	User save(UserDto dto);
	void update(UserDto dto);
	void delete(int id);
	public Page<User> findAllPaging(Pageable pageAble);
	public Page<User> pagingUserDto(int pageIndex, int pageSize);
	
    public static final int MAX_FAILED_ATTEMPTS = 3;
    
    public static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

     
    public void increaseFailedAttempts(User user); 
     
    public void resetFailedAttempts(String email);
     
    public void lock(User user);
     
    public boolean unlockWhenTimeExpired(User user);
}
