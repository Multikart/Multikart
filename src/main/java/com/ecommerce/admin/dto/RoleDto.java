package com.ecommerce.admin.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class RoleDto {
	private int id;
	private String name;
	private String description;
	private String validflag;
	private String generate;
	private String useradd;
	private Date datime;
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
	
	public Date getDatime() {
		return datime;
	}
	public void setDatime(Date datime) {
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
