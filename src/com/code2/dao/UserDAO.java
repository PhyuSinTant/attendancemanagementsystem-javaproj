package com.code2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.code2.model.Teacher;
import com.code2.model.User;

public class UserDAO {

	private DataSource dataSource;

	public UserDAO(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public User getUserByUsernameAndPassword(String username,String password) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from users where username=? and password=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet result = stmt.executeQuery();
		User user=null;
		if(result.next()) {
			int id = result.getInt("id");
			String uName = result.getString("username");
			String psw = result.getString("password");
			String role = result.getString("role");
			user= new User(id,uName,psw,role);
			
		}
		
		return user;
	}
	
	public User getUserById(int id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from users where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		User user=null;
		if(result.next()) {
			int userId = result.getInt("id");
			String name = result.getString("username");
			String psw = result.getString("password");
			String role = result.getString("role");
			 user = new User(userId,name,psw,role);
			
		}
		return user;
	}
	
	public void updateUser(User user) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "update users set username=?,password=? where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setInt(3, user.getId());
		
		stmt.executeUpdate();
	}
}
//	public User getUserByUsernameAndPsw(String username,String password) throws SQLException {
//		Connection conn = dataSource.getConnection();
//		String sql = "select * from users where username=? and password=?";
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setString(1, username);
//		stmt.setString(2, password);
//		ResultSet result = stmt.executeQuery();
//		User user=null;
//		if(result.next()) {
//			int Id = result.getInt("id");
//			String un = result.getString("username");
//			String pass = result.getString("password");
//			String role = result.getString("role");
//			user=new User(Id,username,password,role);
//		}
//		return user;
//		
//	}

