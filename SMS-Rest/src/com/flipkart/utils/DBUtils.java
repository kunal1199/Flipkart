package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            	Properties prop = new Properties();
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                
                //initialize the driver
                Class.forName(driver);
                
                //set up the connection
                connection = DriverManager.getConnection(url, user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
            	e.printStackTrace();
            } catch (FileNotFoundException e) {
            	e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            }
            return connection;
        }

    }


}


