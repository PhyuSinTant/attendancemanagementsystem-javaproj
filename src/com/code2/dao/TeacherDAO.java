package com.code2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.code2.model.Teacher;

public class TeacherDAO {

	private DataSource dataSource;

	public TeacherDAO(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public void saveTeacher(Teacher teacher) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "insert into teachers (name,img_url,department) value (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, teacher.getName());
		stmt.setString(2, teacher.getImg_url());
		stmt.setString(3, teacher.getDepartment());
		stmt.executeUpdate();
		
	}
	
	public List<Teacher> getTeachers() throws SQLException {
		List<Teacher> list = new ArrayList<>();
		Connection conn = dataSource.getConnection();
		String sql  = "select * from teachers";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		while(result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			String imgUrl = result.getString("img_url");
			String dept = result.getString("department");
			Teacher teacher = new Teacher(id,name,imgUrl,dept);
			list.add(teacher);
		}
		return list;
		
	}
	
	public void deleteTeacher(int id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "delete from teachers where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		
	}
	
	public Teacher getTeacherById(int id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from teachers where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		Teacher teacher=null;
		if(result.next()) {
			int teacherId = result.getInt("id");
			String name = result.getString("name");
			String imgUrl = result.getString("img_url");
			String dept = result.getString("department");
			 teacher = new Teacher(teacherId,name,imgUrl,dept);
			
		}
		return teacher;
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
}
