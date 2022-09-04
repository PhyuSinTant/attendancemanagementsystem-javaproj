package com.code2.model;

public class Info {
	private int teacherID;
	private String year;
	private String major;
	private String subject;
	
	
	public Info(String year, String major, String subject) {
		super();
		this.year = year;
		this.major = major;
		this.subject = subject;
	}


	public Info(int teacherID, String year, String major, String subject) {
		super();
		this.teacherID = teacherID;
		this.year = year;
		this.major = major;
		this.subject = subject;
	}


	public int getTeacherID() {
		return teacherID;
	}


	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
