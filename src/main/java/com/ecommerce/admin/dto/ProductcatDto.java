package com.ecommerce.admin.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

public class ProductcatDto {

	private int id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	private String validflag;
	private Timestamp datime;
	private String useradd;
	private String image;
	private String imagePath;
	
	
	
	public ProductcatDto() {
		super();
	}
	
	public ProductcatDto(int id, String name, String description, String validflag, Timestamp datime, String useradd,
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
