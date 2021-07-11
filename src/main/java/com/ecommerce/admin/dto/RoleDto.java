package com.ecommerce.admin.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

public class RoleDto {
	
	private int id;
	
	@NotBlank(message="Name can not blank")
	private String name;
	@NotBlank(message="Description can not blank")
	private String description;
	private String validflag;
	private String generate;
	private String useradd;
	private Timestamp datime;
	
	public RoleDto() {
		
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
	
	public Timestamp getDatime() {
		return datime;
	}
	public void setDatime(Timestamp datime) {
		this.datime = datime;
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
	public String getGenerate() {
		return generate;
	}
	public void setGenerate(String generate) {
		this.generate = generate;
	}
	public String getUseradd() {
		return useradd;
	}
	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}
//	public Timestamp getDatime() {
//		return datime;
//	}
//	public void setDatime(Timestamp datime) {
//		this.datime = datime;
//	}
}
