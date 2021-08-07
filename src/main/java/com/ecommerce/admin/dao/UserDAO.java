package com.ecommerce.admin.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
	
	/* @Query("SELECT u FROM User u WHERE u.id = :id AND u.validflag = 1") */
//	public User findById(@Param("id")int id);
	public User findById(int id);
	public User findByFirstname(String firstname);
	public User findByEmail(String email);
	public Page<User> findByValidflagContaining(String validflag, Pageable pageAble);
	//public Page<User> findAllPaging(Pageable pageAble);
//	public List<User> custom();
	
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String email);
}
