package com.flipkart.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//class for setting up database connectivity
public class DBUtils {
	//setting up the connection here
	private static Connection connection = null;
	//adding a logger here
	public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	// read from the config file
            	//Properties prop = new Properties();
                //InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
                //prop.load(inputStream);
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost/test_sms";
                String user = "root";
                String password = "kunal132";
                
                //initialize the driver
                Class.forName(driver);
                
                //set up the connection
                connection = DriverManager.getConnection(url, user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
            return connection;
        }

    }


}


