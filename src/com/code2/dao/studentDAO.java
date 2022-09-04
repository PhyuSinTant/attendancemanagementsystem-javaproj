package com.code2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.code2.model.Student;
import com.code2.model.Teacher;

public class studentDAO {

	private DataSource dataSource;

	public studentDAO(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudentsBySubMj(Student s) throws SQLException {
		List<Student> list = new ArrayList<>();
		Connection conn = dataSource.getConnection();
		String sql  = "SELECT * FROM students WHERE major=? AND year=?";
				
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,s.getMajor());
		stmt.setString(2, s.getYear());
		ResultSet result = stmt.executeQuery();
		while(result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			int rn = result.getInt("rollNo");
			String major = result.getString("major");
			String year = result.getString("year");
			Student stu = new Student(id,name,rn,major,year);
			list.add(stu);
		}
		return list;
		
	}
	public void updateTeacher(Teacher teacher) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "update teachers set name=?,img_url=?,department=? where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,teacher.getName());
		stmt.setString(2, teacher.getImg_url());
		stmt.setString(3, teacher.getDepartment());
		stmt.setInt(4, teacher.getId());
		stmt.executeUpdate();
	}
	public void updateAttendance(String columnName,int value,String id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "update students set "+columnName+"=? where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,value);
		stmt.setString(2, id);
		stmt.executeUpdate();
		
		stmt.executeUpdate();
		
	}
	
	public int getAttendance(String columnName,int id) throws SQLException 
	{
		Connection conn = dataSource.getConnection();
		String sql = "select "+columnName+" from students where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet result  = stmt.executeQuery();
		int attendance=0;
		if(result.next()) {
			 attendance = result.getInt(columnName);
		}
		
		return attendance;
	}

	public int getAttendance(String sub, int[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

