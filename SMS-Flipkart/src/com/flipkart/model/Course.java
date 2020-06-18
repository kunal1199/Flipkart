package com.flipkart.model;

import java.sql.Timestamp;

//Model class for a course
public class Course {
	int id;
	String name;
	int fees;
	String status;
	Timestamp date;
	String mode;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Course(int id, String status, Timestamp timestamp, String string) {
		this.id = id;
		this.status = status;
		this.date = timestamp;
		this.mode = string;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Course(int id2, String name2, int fees2) {
		// TODO Auto-generated constructor stub
		id = id2;
		name = name2;
		fees= fees2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
