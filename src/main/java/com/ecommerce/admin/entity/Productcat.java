package com.ecommerce.admin.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Productcat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String validflag;
	private Timestamp datime;
	private String useradd;
	private String image;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productcat")
	private List<Product> listProduct;
	
	
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public Productcat() {
		super();
	}
	public Productcat(int id, String name, String description, String validflag, Timestamp datime, String useradd,
			String image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.validflag = validflag;
		this.datime = datime;
		this.useradd = useradd;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValidflag() {
		return validflag;
	}
	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}
	public Timestamp getDatime() {
		return datime;
	}
	public void setDatime(Timestamp datime) {
		this.datime = datime;
	}
	public String getUseradd() {
		return useradd;
	}
	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
