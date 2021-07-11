package com.ecommerce.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dto.RoleDto;

public interface RoleService  {
	public List<RoleDto> findAllOrderByDatimeDesc();
	public RoleDto findById(int id);
	public RoleDto findByName(String name);
	public List<RoleDto> getListRole();
	public List<RoleDto> findAllPagingSortByDatimeDesc(Pageable pageable);
	public boolean save(RoleDto roledto);
	public boolean edit(RoleDto roledto);
	public boolean delete(int id);
	public boolean invalidRole(int id);
}
