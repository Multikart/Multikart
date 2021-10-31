package com.ecommerce.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ecommerce.admin.dto.ProductcatDto;
import com.ecommerce.admin.entity.Productcat;

public interface ProductcatService {

	public List<ProductcatDto> findAllOrderByDatimeDesc();
	public ProductcatDto findById(int id);
	public ProductcatDto findByName(String name);
	public List<ProductcatDto> getListProductcat();
	public List<ProductcatDto> findAllPagingSortByDatimeDesc(Pageable pageable);
	public Productcat save(ProductcatDto catdto);
	public Productcat edit(ProductcatDto catdto);
	public boolean delete(int id);
	public boolean invalidProductcat(int id);
}
