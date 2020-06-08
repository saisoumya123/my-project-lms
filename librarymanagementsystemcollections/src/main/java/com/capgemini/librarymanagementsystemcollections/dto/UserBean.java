package com.capgemini.librarymanagementsystemcollections.dto;

public class UserBean {
	private String name;
	private int id;
	private String mobile;
	private String email;
	private String password;
	private String role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String regMobile) {
		this.mobile = regMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserBean(String name, int id, String mobile, String email, String password, String role) {
		super();
		this.name = name;
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	
}
