package com.ecommerce.admin.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productcode;
	private String name;
	private String description;
	private String image;
	private BigDecimal price;
	private String size;
	private String status;
	private String validflag;
	private Timestamp datime;
	private String useradd;
	
	@Column(name = "category_id")
	private int category_id;
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Productcat productcat;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "product")
	private List<Image> listImage;

	public Product() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public Productcat getProductcat() {
		return productcat;
	}

	public void setProductcat(Productcat productcat) {
		this.productcat = productcat;
	}
	
	
}
