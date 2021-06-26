package com.ecommerce.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public List<RoleDto> getListRole() {
		
		return mappingRoleToRoleDTO(roleDAO.findAll());
	}
	
	public List<RoleDto> mappingRoleToRoleDTO(List<Role> listRole){
		List<RoleDto> listRoleDtpo = new ArrayList<RoleDto>();
		RoleDto roleDto = null;
		
		if(!listRole.isEmpty()) {
			for(Role role : listRole) {
				roleDto = new RoleDto();
				roleDto.setId(role.getId());
				roleDto.setDescription(role.getDescription());
				roleDto.setName(role.getName());
				listRoleDtpo.add(roleDto);
			}
		}
		
		return listRoleDtpo;
	}

}
