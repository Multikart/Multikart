package com.ecommerce.admin.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r FROM Role r")
	public Page<Role> findAllPaging(Pageable pageable);
	@Query("SELECT r from Role r ORDER BY r.datime DESC")
	public List<Role> findAllOrderByDatimeDesc();
	public Role findByName(String name);


}
