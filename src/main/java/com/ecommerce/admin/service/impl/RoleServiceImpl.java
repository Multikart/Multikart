package com.ecommerce.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
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

	@Override
	public List<RoleDto> findAllPagingSortByDatimeDesc(Pageable pageable) {
		Page<Role> pageRole = roleDAO.findAllPaging(pageable);
		List<Role> roles = pageRole.getContent();
		return mappingRoleToRoleDTO(roles);
	}

	@Override
	public boolean save(RoleDto roledto) {
		if(roledto.getId() != 0) {
			LOGGER.debug("Role is Exist");
			return false ;
		}
		try {
			roleDAO.save(dtoToEntity(roledto));
		}catch (Exception e) {
			LOGGER.debug(e.getMessage());
			return false;
		}
		LOGGER.debug("Create Role successfully!");
		return true;
	}

	@Override
	public boolean edit(RoleDto roledto) {
		try {
			Role role = roleDAO.getById(roledto.getId());
			if(role == null) {
				LOGGER.debug("Role is not Exist");
				return false;
			}
			roleDAO.save(dtoToEntity(roledto));
		}catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.debug("Edit Role successfully!");
		return true;
	}

	@Override
	public boolean delete(int id) {
		if(id == 0) {
			LOGGER.debug("id not found");
			return false;
		}
		try {
			roleDAO.deleteById(id);
		}catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.debug("Delete Role successfully!");
		return true;
	}
	
	public Role dtoToEntity(RoleDto roledto) {
		Role role = null;
		if(null != roledto && roledto.getId()!=0) {
			role = roleDAO.getById(roledto.getId());
		}else {
			role = new Role();
		}
		role.setDescription(roledto.getDescription());
		role.setName(roledto.getName());
		role.setValidflag(roledto.getValidflag());
		return role;
	}
	
	public RoleDto entityToDto(Role role) {
		if(role == null) {
			return null;
		}
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDescription(role.getDescription());
		dto.setGenerate(role.getGenerate());
		dto.setValidflag(role.getValidflag());
		dto.setDatime(role.getDatime());
		dto.setUseradd(role.getUseradd());
		return dto;
	}

	@Override
	public RoleDto findById(int id) {
		Role role = roleDAO.getById(id);
		return entityToDto(role);
	}

	@Override
	public List<RoleDto> findAllOrderByDatimeDesc() {
		List<Role> roles = roleDAO.findAllOrderByDatimeDesc();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		roles.stream().forEach(role->dtos.add(entityToDto(role)));
		return dtos;
	}

	@Override
	public RoleDto findByName(String name) {
		Role role = null;
		role = roleDAO.findByName(name);
		if(role!=null) {
			return entityToDto(role);
		}
		return null;
	}

	@Override
	public boolean invalidRole(int id) {
		Optional<Role> role = roleDAO.findById(id);
		role.ifPresent(p->{
			p.setValidflag("2");
			try {
				roleDAO.save(p);
			} catch (Exception e) {
				// TODO: handle exception
			}
			});
		return false;
	}
	

}
