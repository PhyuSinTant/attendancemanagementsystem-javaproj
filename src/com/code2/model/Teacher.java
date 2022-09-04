package com.code2.model;

public class Teacher {

	private int id;
	private String name;
	private String img_url;
	private String department;
	
	
	public Teacher(String name, String img_url, String department) {
		super();
		this.name = name;
		this.img_url = img_url;
		this.department = department;
	}

	public Teacher(int id, String name, String img_url, String department) {
		super();
		this.id = id;
		this.name = name;
		this.img_url = img_url;
		this.department = department;
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
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
