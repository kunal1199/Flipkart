package com.flipkart.model;

import org.apache.log4j.Logger;

//Professor class inherits User
public class Professor extends User{
	String name;
	String phone;
	String email;
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(Professor.class);
	
	//Professor class contructor
	public Professor(String name, String phone, String email) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	//inspect a Professor
	public void getDetails() {
		logger.info(String.format("Name: %12s  Phone: %10s  Email: %10s", name, phone, email));
	}

}
