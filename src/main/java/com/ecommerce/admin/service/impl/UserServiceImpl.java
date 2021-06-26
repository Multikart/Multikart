package com.ecommerce.admin.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.admin.dao.UserDAO;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO UserDao;
	//@Autowired
	//EntityManagerFactory entityManagerFactory;
	private UserDto userdto = null;
	
	
	@Override
	public List<UserDto> findAll() {
//		List<UserDto> userDtos = new ArrayList();
//		List<User> users = new ArrayList<User>();
//		users.add(UserDao.findById(2));
//		users.add(UserDao.findById(1));
//		users.add(UserDao.findByFirstname("dac"));
//		users.add(UserDao.findByFirstname("dao"));
		return convertEntityToDtoList(UserDao.findAll());
	}

	@Override
	public UserDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserDto dto) {
		// Mã hóa mật khẩu
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashed);
		UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void update(UserDto dto) {
		UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void delete(int id) {
		UserDao.deleteById(id);
		
	}
	
	public List<UserDto> convertEntityToDtoList(List<User> users){
		List<UserDto> dtos = new ArrayList<>();
		for(User user:users) {
			userdto = new UserDto();
			convertEntityToDto(user);
			dtos.add(userdto);
		}
		return dtos;
	}
	
	public UserDto convertEntityToDto(User user) {
		userdto = new UserDto();
		userdto.setId(user.getId());
		userdto.setFirstname(user.getFirstname());
		userdto.setLastname(user.getLastname());
		userdto.setAddress(user.getAddress());
		userdto.setAvatar(user.getAvatar());
//		userdto.setDatime(new Timestamp(System.currentTimeMillis()) );
		userdto.setEmail(user.getEmail());
		userdto.setJoindate(user.getJoindate());
		userdto.setPassword(user.getPassword());
		userdto.setPhone(user.getPhone());
		userdto.setUseradd(user.getUseradd());
		userdto.setValidflag(user.getValidflag());
		return userdto;
	}
	
	public User convertDtoToEntity(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setAddress(dto.getAddress());
		user.setAvatar(dto.getAvatar());
//		user.setDatime(dto.getDatime());
		user.setEmail(dto.getEmail());
		user.setJoindate(dto.getJoindate());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setUseradd(dto.getUseradd());
		user.setValidflag(dto.getValidflag());
		user.setRoleId(dto.getRoleId());
		return user;
	}

	@Override
	public Page<User> findAllPaging(Pageable pageable) {
		return UserDao.findAll(pageable);
	}


}
