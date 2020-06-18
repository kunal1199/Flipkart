package com.flipkart.model;

import org.apache.log4j.Logger;

//Admin class inherits User class
public class Admin extends User {
	String name;
	String phone;
	String email;
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(Admin.class);
	
	//Admin class constructor
	public Admin(String name, String phone, String email) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	//Inspect an admin
	public void getDetails() {
		logger.info(String.format("Name: %12s  Phone: %10s  Email: %10s", name, phone, email));
	}

}
