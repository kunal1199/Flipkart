package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;

//class for interacting with the sql database for user login functionalities
public class UserDao implements IUserDao {
	public static Connection conn = null;
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(UserDao.class);
	//return role of the user using the username and password combination
	public String checkIdentity(String username, String password) {
		try {
			// sql statement to check for an entry in the users table
			String sql = SQLQueries.GET_ROLE;
			//initialize connection using the setup done in dbutils class
			conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2,  password);
			ResultSet rs = stmt.executeQuery();
			String role = "";
			while(rs.next()) {
				role=rs.getString("type");
			}
			return role;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	//fetch userid for specific functions of the user
	public int fetchId(String username, String password) {
		// TODO Auto-generated method stub
		try {
			// sql statement to check for an entry in the users table
			String sql = SQLQueries.FETCH_USER_ID;
			conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2,  password);
			ResultSet rs = stmt.executeQuery();
			int id = -1;
			while(rs.next()) {
				id=rs.getInt("id");
			}
			return id;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			return -1;
		}
	}
}
