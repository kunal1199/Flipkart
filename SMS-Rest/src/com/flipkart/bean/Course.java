package com.flipkart.bean;

//bean class for course
public class Course {
	//members of the class
	int id;
	int catalogId;
	String name;
	int fees;
	//2 constructors
	public Course() {}
	public Course(int id, int catalogId, String name, int fees) {
		this.id=id;
		this.catalogId=catalogId;
		this.name=name;
		this.fees=fees;
	}
	//getters and setters
	public int getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
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
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
}
