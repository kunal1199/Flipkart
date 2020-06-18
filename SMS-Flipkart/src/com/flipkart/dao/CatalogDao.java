package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueries;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtils;

//class for interacting with the sql database for catalog related functionalities
public class CatalogDao implements ICatalogDao {
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(CatalogDao.class);
	//initialize connection using the setup done in dbutils class
	Connection conn = DBUtils.getConnection();
	//fetch the catalog from the catalog table
	public List<Course> fetchCatalog() {
		List <Course> courses = new ArrayList <>();
		try {
			String sql=SQLQueries.VIEW_CATALOG;
			PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         String name = rs.getString("course name");
		         int fees = rs.getInt("fees");
		         courses.add(new Course(id, name, fees));
		      }
		      return courses;
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	//get name of the course using the course id
	public String viewCourseById(Integer id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_COURSES_BY_ID;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

		      //Extract data from result set
			  String course="";
		      while(rs.next()){
		         //Retrieve by column name
		         course = rs.getString("course name");
		      }
		      return course;
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	//get fees for a course using the id of the course
	public int getFees(Integer id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_COURSES_BY_ID;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

		      //Extract data from result set
			  int fees = 0;
		      while(rs.next()){
		         //Retrieve by column name
		         fees = rs.getInt("fees");
		      }
		      return fees;
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
