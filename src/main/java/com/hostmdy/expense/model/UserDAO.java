package com.hostmdy.expense.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.hostmdy.expense.utility.PasswordEncoder;
import com.hostmdy.expense.utility.PasswordValidator;

public class UserDAO {
	
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
/*
	public Optional<User> getUserByEmail(String email){
		Optional<User> userOptional = Optional.empty();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where email='" + email + "';");
			
			while(rs.next()) {
				userOptional = Optional.of(new User(
						rs.getLong("id"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("username"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime()
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return userOptional;
	}
*/
	
	public List<User> getAllUsers(){
		
		List<User> userList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where role='user';");
			
			while(rs.next()) {
				userList.add(new User(
						rs.getLong("id"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("username"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime()
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return userList;
	}
	
	
	public Optional<User> getUserByUsernameOrEmail(String username){
		Optional<User> userOptional = Optional.empty();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where username='" + username + "' or email='"+ username + "';");
			
			while(rs.next()) {
				userOptional = Optional.of(new User(
						rs.getLong("id"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("username"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime()
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return userOptional;
	}
	
	public boolean createUser(User user) {
		boolean insertOk = false;
		
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into user "
					+ "(firstname,lastname,username,email,password,role,enable,createdAt) "
					+ "values(?,?,?,?,?,?,?,?);");
			pStmt.setString(1, user.getFirstname());
			pStmt.setString(2, user.getLastname());
			pStmt.setString(3, user.getUsername());
			pStmt.setString(4, user.getEmail());
			try {
				pStmt.setString(5, PasswordEncoder.encode(user.getPassword()));
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(6, user.getRole());
			pStmt.setBoolean(7, user.getEnable());
			pStmt.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
			
			int rowEffected = pStmt.executeUpdate();
			
			if(rowEffected > 0) {
				insertOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return insertOk;
	}
	
	
	public boolean authenticate(String username, String password){
		Optional<User> userOptional = getUserByUsernameOrEmail(username);
		
		if(userOptional.isEmpty()) {
			return false;
		}
		
		User user = userOptional.get();
		try {
			if(PasswordValidator.validatePassword(password, user.getPassword()) && user.getEnable()) {
				return true;
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean disableUser(Long userId) {
		boolean disableOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, false);
			pStmt.setLong(2, userId);
			
			int rowEffected = pStmt.executeUpdate();
			if (rowEffected > 0) {
				disableOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return disableOk;
	}
	
	public boolean enableUser(Long userId) {
		boolean enableOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, true);
			pStmt.setLong(2, userId);
			
			int rowEffected = pStmt.executeUpdate();
			if (rowEffected > 0) {
				enableOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return enableOk;
	}
	
	

}
