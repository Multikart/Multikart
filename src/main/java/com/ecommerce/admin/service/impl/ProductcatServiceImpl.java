package com.ecommerce.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.ProductcatDAO;
import com.ecommerce.admin.dto.ProductcatDto;
import com.ecommerce.admin.entity.Productcat;
import com.ecommerce.admin.service.ProductcatService;

@Service
public class ProductcatServiceImpl implements ProductcatService {

	private static final Logger logger = LoggerFactory.getLogger(ProductcatServiceImpl.class);
	@Autowired
	ProductcatDAO productcatDAO;

	@Override
	public List<ProductcatDto> findAllOrderByDatimeDesc() {
		List<Productcat> entities = productcatDAO.findAllOrderByDatimeDesc();
		List<ProductcatDto> dtos = convertListEntityToListDto(entities);
		logger.info("findAllOrderByDatimeDesc()");
		return dtos;
	}

	@Override
	public ProductcatDto findById(int id) {
		Optional<Productcat> entity = productcatDAO.findById(id);
		ProductcatDto dto = convertEntityToDto(entity.get());
		return dto;
	}

	@Override
	public ProductcatDto findByName(String name) {
		Productcat entity = productcatDAO.findByName(name);
		ProductcatDto dto = null;
		if (entity != null) {
			dto = convertEntityToDto(entity);
		}
		return dto;
	}

	@Override
	public List<ProductcatDto> getListProductcat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductcatDto> findAllPagingSortByDatimeDesc(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Productcat save(ProductcatDto catdto) {
		ProductcatDto dto = findByName(catdto.getName());
		// Check if Category is exist
		if (dto != null) {
			return null;
		}
		Productcat cat = new Productcat();
		convertDtoToEntity(catdto,cat);
		// save
		Productcat productcat = productcatDAO.save(cat);
		return productcat;
	}

	@Override
	public Productcat edit(ProductcatDto catdto) {
		Optional<Productcat> catOption = productcatDAO.findById(catdto.getId());
		// Check if Category is not exist
		if(!catOption.isPresent()) {
			return null;
		}
		convertDtoToEntity(catdto,catOption.get());
		// save
		Productcat productcat = productcatDAO.save(catOption.get());
		return productcat;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidProductcat(int id) {
		Productcat entity = productcatDAO.getById(id);
		entity.setValidflag("2");
		productcatDAO.save(entity);
		return true;
	}

	public List<ProductcatDto> convertListEntityToListDto(List<Productcat> listEntity) {
		List<ProductcatDto> dtos = new ArrayList<ProductcatDto>();
		listEntity.stream().forEach(e -> {
			ProductcatDto dto = convertEntityToDto(e);
			dtos.add(dto);
		});
		return dtos;
	}

	public void convertDtoToEntity(ProductcatDto dto, Productcat entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImage(!dto.getImage().equals(entity.getImage())?dto.getImage():entity.getImage());
		entity.setUseradd(dto.getUseradd());
		entity.setValidflag(dto.getValidflag());
//		return entity;
	}

	public ProductcatDto convertEntityToDto(Productcat entity) {
		ProductcatDto dto = new ProductcatDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
//		dto.setDatime(entity.getDatime());
		dto.setImage(entity.getImage());
		dto.setUseradd(entity.getUseradd());
		dto.setValidflag(entity.getValidflag());
		if(!entity.getImage().isBlank()) {
			dto.setImagePath("../static/category-photos/" + entity.getUseradd() + "/" + entity.getImage());
		}else {
			dto.setImagePath("../static/assets/images/dashboard/user1.jpg");
		}
		
		return dto;
	}
}
