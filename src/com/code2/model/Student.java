package com.code2.model;

public class Student {

	private int id;
	private String name;
	private int rollNo;
	private String major;
	private String year;
	
	
	public Student(String major, String year) {
		super();
		this.major = major;
		this.year = year;
	}

	public Student(String name, int rollNo, String major, String year) {
		super();
		this.name = name;
		this.rollNo = rollNo;
		this.major = major;
		this.year = year;
	}
	
	public Student(int id, String name, int rollNo, String major, String year) {
		super();
		this.id = id;
		this.name = name;
		this.rollNo = rollNo;
		this.major = major;
		this.year = year;
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
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
