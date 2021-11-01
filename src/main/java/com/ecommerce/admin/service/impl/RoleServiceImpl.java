package com.ecommerce.admin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.dao.RoleDAO;
import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.dto.RoleThreadUpload;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
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
	public Role save(RoleDto roledto) {
		Role role = null;
		if(roledto.getId() != 0) {
			LOGGER.debug("Role is Exist");
			return role ;
		}
		try {
			role = roleDAO.save(dtoToEntity(roledto));
		}catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.debug("Create Role successfully!");
		return role;
	}

	@Override
	public Role edit(RoleDto roledto) {
		Optional<Role> roleOption = roleDAO.findById(roledto.getId());
		Role role = null;
		try {
			if(roleOption == null) {
				LOGGER.debug("Role is not Exist");
				return role;
			}
			role = roleDAO.save(dtoToEntity(roledto));
		}catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.debug("Edit Role successfully!");
		return role;
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
		if(roledto.getId()!=0) {
			Optional<Role> roleOption = roleDAO.findById(roledto.getId());
			if(!roleOption.isPresent()) {
				return null;
			}
			role = roleOption.get();
		}else {
			return role;
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
//		dto.setDatime(role.getDatime());
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

	@Override
	public void uploadRole(MultipartFile uploadFile) {
		InputStream inputStream;
		try {
			inputStream = uploadFile.getInputStream();
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			List<Role> roles = new ArrayList<Role>();
			while(iterator.hasNext()) {
				Row nextRow = iterator.next();
				if(nextRow.getRowNum()==0
						|| nextRow.getCell(0).toString()==""
						|| nextRow.getCell(1).toString()==""
						|| nextRow.getCell(2).toString()=="") {
					continue;
				}
				
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Role role = new Role();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell.getColumnIndex()==0) {
						role.setName(cell.toString());
					}else if(cell.getColumnIndex()==1){
						role.setDescription(cell.toString());
					}else if(cell.getColumnIndex()==2){
						if(Double.parseDouble(cell.toString())==1) {
							role.setValidflag("1");
						}else {
							role.setValidflag("2");
						}
					}
				}
				roles.add(role);
			}
			ExecutorService executorService = Executors.newFixedThreadPool(8);
			CountDownLatch countDownLatch = new CountDownLatch(roles.size());
			long ts = System.currentTimeMillis();
			for(Role role : roles) {
				RoleThreadUpload rolethread = new RoleThreadUpload(role,countDownLatch);
				applicationContext.getAutowireCapableBeanFactory().autowireBean(rolethread);
				executorService.submit(rolethread);
			}
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOGGER.info("Time to finish = " + (System.currentTimeMillis() - ts));
			
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	
	}
	

}
