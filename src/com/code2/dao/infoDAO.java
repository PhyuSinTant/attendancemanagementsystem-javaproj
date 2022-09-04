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
import com.code2.model.User;
import com.code2.model.Info;

public class infoDAO {
	private DataSource dataSource;

	public infoDAO(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}

	public void saveTeacher(int id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "insert into info (teacherID) value (?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,id);
		
		stmt.executeUpdate();
		
	}
	
//	public Info getInfoById(int id) throws SQLException {
//		Connection conn = dataSource.getConnection();
//		String sql = "select * from info where teacherID=?";
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setInt(1, id);
//		ResultSet result = stmt.executeQuery();
//		Info info=null;
//		while(result.next()) {
//			int teachID = result.getInt("teacherID");
//			String year = result.getString("year");
//			String major = result.getString("major");
//			String subject = result.getString("subject");
//			 info = new Info(teachID,year,major,subject);
//			
//		}
//		return info;
//		
//	}
	
	
	public List<Info> getInfoById(int id) throws SQLException {
		List<Info> list = new ArrayList<>();
		Connection conn = dataSource.getConnection();
		String sql  = "select * from info where teacherID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		while(result.next()) {
			int teachID = result.getInt("teacherID");
			String year = result.getString("year");
			String major = result.getString("major");
			String subject = result.getString("subject");
			 Info info = new Info(teachID,year,major,subject);
			
			list.add(info);
		}
		return list;
		
	}
	
}
