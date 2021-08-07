package com.ecommerce.admin.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.admin.dao.UserDAO;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.UserService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO UserDao;
	//@Autowired
	//EntityManagerFactory entityManagerFactory;
	private UserDto userdto = null;
	
	
    public static final int MAX_FAILED_ATTEMPTS = 3;
    
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

     
    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        UserDao.updateFailedAttempts(newFailAttempts, user.getEmail());
    }
     
    public void resetFailedAttempts(String email) {
    	UserDao.updateFailedAttempts(0, email);
    }
     
    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
         
        UserDao.save(user);
    }
     
    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
         
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
             
            UserDao.save(user);
             
            return false;
        }
         
        return true;
    }
	
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
		
		return convertEntityToDto(id);
	}
	
	private UserDto convertEntityToDto(int id) {
		User user = UserDao.findById(id);
		UserDto userDto = null;
		if(user != null) {
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstname(user.getFirstname());
			userDto.setLastname(user.getLastname());
			userDto.setAddress(user.getAddress());
			userDto.setAvatar(user.getAvatar());
			userDto.setJoindate(user.getJoindate());
			userDto.setEmail(user.getEmail());
			userDto.setJoindate(user.getJoindate());
			userDto.setPassword(user.getPassword());
			userDto.setPhone(user.getPhone());
			userDto.setRoleId(user.getRoleId());
			userDto.setUseradd(user.getUseradd());
			userDto.setValidflag(user.getValidflag());
		}
		return userDto;
	}

	@Override
	public User save(UserDto dto) {
		// Mã hóa mật khẩu
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashed);
		return UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void update(UserDto dto) {
		UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void delete(int id) {
		User user = UserDao.findById(id);
		user.setValidflag("2");
		UserDao.save(user);
		
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
	
	private int convertDateTime() {
		
		//get Date + Hourse (27062021_113300)
		LocalDateTime myDateFormat = LocalDateTime.now();
		
		//Format DDMMYYY
		DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ofPattern("ddMMYYYY");
		String formatDate = myDateFormat.format(dateTimeFormat);
		
		//Parse to String to Int
		int joinDatetime = Integer.parseInt(formatDate);
		
		return joinDatetime;
	}
	
	public UserDto convertEntityToDto(User user) {
		userdto = new UserDto();
		userdto.setId(user.getId());
		userdto.setFirstname(user.getFirstname());
		userdto.setLastname(user.getLastname());
		userdto.setAddress(user.getAddress());
		userdto.setAvatar(user.getAvatar());
		userdto.setJoindate(user.getJoindate());
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
		user.setValidflag("1");
		user.setJoindate(convertDateTime());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setUseradd(dto.getUseradd());
		user.setRoleId(dto.getRoleId());
		return user;
	}

	@Override
	public Page<User> findAllPaging(Pageable pageable) {
		return UserDao.findAll(pageable);
	}

	@Override
	public Page<User> pagingUserDto(int pageIndex, int pageSize) {
		//PageAble container vi tri trang duoc lay, va so phan tu duoc lay
		Pageable pageAble = PageRequest.of(pageIndex - 1, pageSize);
		
		return UserDao.findByValidflagContaining("1", pageAble);
		
	}


}
