package com.ecommerce.admin.service.impl;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.entity.Role;



class RoleServiceTest {

	@InjectMocks
	RoleServiceImpl roleService;
	@Mock
	RoleDAO roleDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	@Test
//	void testGetListRole() {
//		Mockito.when(roleDAO.findAll()).thenReturn(new ArrayList<Role>());
//		List<RoleDto> roles = roleService.getListRole();
//	}
//	
//	@Test
//	void testMappingRoleToRoleDTOWithEmpty() {
//		List<RoleDto> dtos = roleService.mappingRoleToRoleDTO(new ArrayList<Role>());
//		assertTrue(dtos.isEmpty());
//	}
//	
//	@Test
//	void testMappingRoleToRoleDTOWithEmptyReturnNotNull() {
//		List<RoleDto> dtos = roleService.mappingRoleToRoleDTO(new ArrayList<Role>());
//		assertNotNull(dtos);
//	}

//	@Test
//	void testFindAllPagingSortByDatimeDesc() {
//		Sort sort = new Sort("name");
//		Page<Role> pageRole = new PageRequest(1,10,sort);
//		Mockito.when(roleDAO.findAllPaging(Mockito.any(Pageable.class))).then(null);
//	}

//	@Test
//	void testSaveWithValid() {
//		Mockito.when(roleDAO.save(Mockito.any(Role.class))).thenReturn(new Role());
//		RoleDto dto = new RoleDto();
//		dto.setDescription("Test");
//		dto.setId(0);
//		dto.setName("Test");
//		dto.setValidflag("1");
//		Role role = roleService.save(dto);
//		assertNotNull(role);
//	}
//	
//	@Test
//	void testSaveWithNull() {
//		RoleDto dto = null;
//		Assertions.assertThrows(Exception.class, ()-> roleService.save(dto));
//	}
//	
//	@Test
//	void testSaveWithDuplicate() {
//		Mockito.when(roleDAO.save(Mockito.any(Role.class))).thenReturn(new Role());
//		RoleDto dto = new RoleDto();
//		dto.setDescription("Test");
//		dto.setId(1);
//		dto.setName("Test");
//		dto.setValidflag("1");
//		Role role = roleService.save(dto);
//		assertNull(role);
//	}
	
//	@Test
//	void testEditWithNotExist() {
//		Mockito.when(roleDAO.findById(Mockito.anyInt())).thenReturn(null);
//		RoleDto dto = new RoleDto();
//		dto.setDescription("Test");
//		dto.setId(0);
//		dto.setName("Test");
//		dto.setValidflag("1");
//		Role role = roleService.save(dto);
//		assertNull(role);
//	}
//	
//	@Test
//	void testEditWithNull() {
//		RoleDto dto = null;
//		Assertions.assertThrows(Exception.class, () -> roleDAO.findById(dto.getId()));
//	}
//	
//	@Test
//	void testEditWithValid_ThenReturnNotNull() {
//		Role role = new Role();
//		role.setDescription("Test");
//		role.setId(1);
//		role.setName("Test");
//		role.setValidflag("1");
//		Optional<Role> roleOption = Optional.ofNullable(role);
//		Mockito.when(roleDAO.findById(Mockito.anyInt())).thenReturn(roleOption);
//		Mockito.when(roleDAO.save(Mockito.any(Role.class))).thenReturn(new Role());
//		RoleDto dto = new RoleDto();
//		dto.setDescription("Test");
//		dto.setId(0);
//		dto.setName("Test");
//		dto.setValidflag("1");
//		Role roleSave = roleService.save(dto);
//		assertNotNull(role);
//	}
	
//	@Test
//	void testDelete_Id1_ThenReturnTrue() {
//		boolean result = roleService.delete(1);
//		Mockito.verify(roleDAO,times(1)).deleteById(Mockito.anyInt());
//		assertTrue(result);
//	}
//	
//	@Test
//	void testDelete_Id0_ThenReturnFalse() {
//		boolean result = roleService.delete(0);
//		Mockito.verify(roleDAO,times(0)).deleteById(Mockito.anyInt());
//		assertFalse(result);
//	}
//	
//	@Test
//	void testDelete_IdNull_ThenReturnExeception() {
//		Integer id = null;
//		assertThrows(NullPointerException.class, ()->{
//			boolean result = roleService.delete(id);
//		});
//	}
//
//	@Test
//	void testDtoToEntity_Null_ThenReturnException() {
//		assertThrows(NullPointerException.class, ()->{
//			Role role = roleService.dtoToEntity(null);
//		});
//	}
//	
//	@Test
//	void testDtoToEntity_DtoIdEqualZero_ThenReturnNull() {
//		RoleDto dto = new RoleDto();
//		Role role = roleService.dtoToEntity(dto);
//		assertNull(role);
//	}
//	
//	@Test
//	void testDtoToEntity_DtoValid_ThenReturnRole() {
//		RoleDto dto = new RoleDto();
//		Role role = new Role();
//		Optional<Role> roleOption = Optional.of(role);
//		dto.setId(1);
//		when(roleDAO.findById(Mockito.anyInt())).thenReturn(roleOption);
//		assertTrue(roleService.dtoToEntity(dto).getClass().equals(Role.class));
//	}
	
	@Test
	void testDtoToEntity_DtoInValid_ThenReturnRole() {
		RoleDto dto = new RoleDto();
		Optional<Role> roleOption = Optional.empty();
		dto.setId(1);
		when(roleDAO.findById(Mockito.anyInt())).thenReturn(roleOption);
		assertFalse(roleOption.isPresent());
	}

//	@Test
//	void testEntityToDto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllOrderByDatimeDesc() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testInvalidRole() {
//		fail("Not yet implemented");
//	}

}
