package com.ecommerce.admin.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDto {
	private int id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String confirmpassword;
	private String avatar;
	private String phone;
	private String address;
	private Character validflag;
	private Date datime;
	private String useradd;
	private  int joindate;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDatime() {
		return datime;
	}
	public void setDatime(Date datime) {
		this.datime = datime;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Character getValidflag() {
		return validflag;
	}
	public void setValidflag(Character validflag) {
		this.validflag = validflag;
	}
//	public Timestamp getDatime() {
//		return datime;
//	}
//	public void setDatime(Timestamp datime) {
//		this.datime = datime;
//	}
	public String getUseradd() {
		return useradd;
	}
	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}
	public int getJoindate() {
		return joindate;
	}
	public void setJoindate(int joindate) {
		this.joindate = joindate;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
