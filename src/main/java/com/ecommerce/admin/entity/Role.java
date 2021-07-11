package com.ecommerce.admin.entity;

import java.sql.Date;
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
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String validflag;
	private String generate;
	private Timestamp datime;
	private String useradd;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;
	
	public Role() {}
	



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
//
//	public void setDatime(Timestamp datime) {
//		this.datime = datime;
//	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Timestamp getDatime() {
		return datime;
	}

	public void setDatime(Timestamp datime) {
		this.datime = datime;
	}
	
}
