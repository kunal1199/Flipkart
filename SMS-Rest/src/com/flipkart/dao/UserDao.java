package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;

//User dao class for interacting with the users table
public class UserDao {
	//setting up the db connection
	Connection conn = DBUtils.getConnection();
	
	//get a list of all the registered users
	public List<User> fetchUsers() {
		// TODO Auto-generated method stub
		List <User> users = new ArrayList <>();
		try {
			String sql=SQLQueries.VIEW_USERS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         int role = rs.getInt("roleid");
		         String username = rs.getString("username");
		         String password = rs.getString("password");
		         String gender = rs.getString("gender");
		         users.add(new User(id, username, password, role, gender));
		      }
		      return users;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//fetch a specific user details
	public User fetchById(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_USER_BY_ID;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

		      //Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	 int userid  = rs.getInt("id");
		         int role = rs.getInt("roleid");
		         String username = rs.getString("username");
		         String password = rs.getString("password");
		         String gender = rs.getString("gender");
		         return new User(userid, username, password, role, gender);
		      }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//add a user to the table
	public void addUser(User user) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.ADD_USER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getRoleId());
			stmt.setString(5, user.getGender());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//drop a user form the table
	public void dropUser(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.DROP_USER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//update details of a user
	public void updateUser(int id, User user) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.UPDATE_USER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getRoleId());
			stmt.setString(5, user.getGender());
			stmt.setInt(6, id);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
