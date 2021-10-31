package com.ecommerce.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.entity.Role;

public interface RoleService  {
	public List<RoleDto> findAllOrderByDatimeDesc();
	public RoleDto findById(int id);
	public RoleDto findByName(String name);
	public List<RoleDto> getListRole();
	public List<RoleDto> findAllPagingSortByDatimeDesc(Pageable pageable);
	public Role save(RoleDto roledto);
	public Role edit(RoleDto roledto);
	public boolean delete(int id);
	public boolean invalidRole(int id);
	public void uploadRole(MultipartFile uploadFile);
}
