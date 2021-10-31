package com.ecommerce.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Productcat;

@Repository
public interface ProductcatDAO extends JpaRepository<Productcat, Integer>{
	@Query("SELECT p FROM Productcat p ORDER BY p.id DESC")
	public List<Productcat> findAllOrderByDatimeDesc();
	public Productcat findByName(String name);
}
