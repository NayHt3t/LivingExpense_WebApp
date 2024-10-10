package com.hostmdy.expense.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemDAO {
	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public ItemDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Item> getAllItemsByUser(Long userID){
		List<Item> itemList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item where user_id='"+userID+"';");
			
			while (rs.next()) {
				itemList.add(new Item(
						rs.getLong("id"), 
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getDouble("price"),
						rs.getDouble("subTotal"),
						rs.getInt("quantity"),
						rs.getBoolean("essential"),
						rs.getTimestamp("issuedDate").toLocalDateTime(),
						rs.getString("description"),
						rs.getString("image"),
						rs.getLong("user_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return itemList;
	}
	
	public Item getAllItemsByID(Long itemID){
		Item item = null;
		
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item where id='"+itemID+"';");
			
			while (rs.next()) {
				item = new Item(
						rs.getLong("id"), 
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getDouble("price"),
						rs.getDouble("subTotal"),
						rs.getInt("quantity"),
						rs.getBoolean("essential"),
						rs.getTimestamp("issuedDate").toLocalDateTime(),
						rs.getString("description"),
						rs.getString("image"),
						rs.getLong("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return item;
	}
	
	public boolean createItem(Item item) {
		boolean insertOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into item "
					+ "(title,category,price,quantity,subTotal,essential,"
					+ "issuedDate,description,image,user_id) "
					+ "values(?,?,?,?,?,?,?,?,?,?);");
			pStmt.setString(1,item.getTitle());
			pStmt.setString(2,item.getCategory());
			pStmt.setDouble(3,item.getPrice());
			pStmt.setInt(4,item.getQuantity());
			pStmt.setDouble(5,item.getSubTotal());
			pStmt.setBoolean(6,item.getEssential());
			pStmt.setTimestamp(7, Timestamp.valueOf(item.getIssuedDate()));
			pStmt.setString(8,item.getDescription());
			pStmt.setString(9,item.getImage());
			pStmt.setLong(10, item.getUser_id());
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected > 0) {
				insertOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return insertOk;
	}
	
	public boolean updateItem(Item item) {
		boolean insertOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update item set "
					+ "title=?,"
					+ "category=?,"
					+ "price=?,"
					+ "quantity=?,"
					+ "subTotal=?,"
					+ "essential=?,"
					+ "description=?,"
					+ "image=? where id=?;"
					);
			pStmt.setString(1,item.getTitle());
			pStmt.setString(2,item.getCategory());
			pStmt.setDouble(3,item.getPrice());
			pStmt.setInt(4,item.getQuantity());
			pStmt.setDouble(5,item.getSubTotal());
			pStmt.setBoolean(6,item.getEssential());		
			pStmt.setString(7,item.getDescription());
			pStmt.setString(8,item.getImage());
			pStmt.setLong(9, item.getId());	
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected > 0) {
				insertOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return insertOk;
	}
	
	public boolean deleteItem(Long itemID) {
		boolean deleteOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from item where id=?;");
			pStmt.setLong(1, itemID);	
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected > 0) {
				deleteOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return deleteOk;
	}
	
	public List<Item> filterItem(String query, long userId) {
		List<Item> filteredList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where `category`='"+query+"' and `user_id`='"+userId+"';");
			
			while (rs.next()) {
				filteredList.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getString("category"),
						rs.getDouble("price"), rs.getDouble("subTotal"), rs.getInt("quantity"),
						rs.getBoolean("essential"), rs.getTimestamp("issuedDate").toLocalDateTime(),
						rs.getString("description"), rs.getString("image"), rs.getLong("user_id")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return filteredList;
	}
	
	

}
