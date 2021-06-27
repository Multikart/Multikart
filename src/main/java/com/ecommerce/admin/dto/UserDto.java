package com.ecommerce.admin.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {
	private int id;
	
	@NotBlank(message = "Email not be blank")
	private String email;
	
	@NotBlank(message = "First Name not be blank")
	private String firstname;

	@NotBlank(message = "Last Name not be blank")
	private String lastname;
	
	@NotBlank(message = "Password not be blank")
	private String password;
	
	@NotBlank(message = "Confirm Password not be blank")
	private String confirmpassword;
	private String avatar;
	private String phone;
	private String address;
	private String validflag;

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
	public String getValidflag() {
		return validflag;
	}
	public void setValidflag(String validflag) {
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
