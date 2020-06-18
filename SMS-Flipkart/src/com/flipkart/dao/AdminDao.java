package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.client.UserClient;
import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;
import com.flipkart.model.Admin;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;

//class for interacting with the sql database for admin functionalities
public class AdminDao implements IAdminDao {
	//logger object initialized for this class
	private static Logger logger = Logger.getLogger(UserClient.class);
	//initialize connection using the setup done in dbutils class
	Connection conn = DBUtils.getConnection();
	//adding a user to the db
	public void addUser(String username, String password, int roleId, String gender) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.ADD_USER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setInt(3, roleId);
			stmt.setString(4,  gender);
			stmt.executeUpdate();
			logger.info("User added!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//removing a user from the db
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.REMOVE_USER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			logger.info("User removed!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//add a course to the course catalog
	public void addCourse(String name, int fees) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.ADD_COURSE_CATALOG;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, fees);
			stmt.executeUpdate();
			logger.info("Course added!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//remove a course from the catalog db
	public void removeCourse(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.REMOVE_COURSE_CATALOG;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			logger.info("Course removed!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//fetch list of users from the db
	public List<User> viewUsers() {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_USERS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<User> users = new ArrayList<>();
			while(rs.next()){
		         //Retrieve by column name
				if(rs.getInt("roleId")==1)
					users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), "student", rs.getString("gender")));
				else if(rs.getInt("roleId")==2)
					users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), "professor", rs.getString("gender")));
				if(rs.getInt("roleId")==3)
					users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), "admin", rs.getString("gender")));
		     }
			logger.info("All users shown!");
			return users;
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	//add a student to the db
	public void addStudent(int userId, String name, String phone, String email, int scholar) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.ADD_STUDENT;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setString(2, name);
			stmt.setString(3, phone);
			stmt.setString(4, email);
			stmt.setInt(5, scholar);
			stmt.executeUpdate();
			logger.info("User added!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//add a professor to the db
	public void addProfessor(int userId, String name, String phone, String email) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.ADD_PROFESSOR;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setString(2, name);
			stmt.setString(3, phone);
			stmt.setString(4, email);
			stmt.executeUpdate();
			logger.info("User added!");
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void inspectUser(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.GET_ROLE_BY_ID;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int roleId=-1;
			while(rs.next()) {
				roleId=rs.getInt("roleid");
			}
			if(roleId==1) {
				stmt = conn.prepareStatement(SQLQueries.INSPECT_STUDENT);
				stmt.setInt(1, id);
				logger.info("Here are the details of the requested student.");
				rs = stmt.executeQuery();
				while(rs.next()){
					new Student(rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getInt("scholarship")).getDetails();
			     }
			} 
			if(roleId==2) {
				stmt = conn.prepareStatement(SQLQueries.INSPECT_PROFESSOR);
				stmt.setInt(1, id);
				logger.info("Here are the details of the requested professor.");
				rs = stmt.executeQuery();
				while(rs.next()){
					new Professor(rs.getString("name"), rs.getString("phone"), rs.getString("email")).getDetails();
			    }
			}
			if(roleId==3) {
				stmt = conn.prepareStatement(SQLQueries.INSPECT_ADMIN);
				stmt.setInt(1, id);
				logger.info("Here are the details of the requested admin.");
				rs = stmt.executeQuery();
				while(rs.next()){
					new Admin(rs.getString("name"), rs.getString("phone"), rs.getString("email")).getDetails();
			    }
			}
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
}
