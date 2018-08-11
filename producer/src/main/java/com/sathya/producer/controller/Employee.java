package com.sathya.producer.controller;

public class Employee {

	private String name;
	private String designation;
	private int sal;

	public Employee() {
	}

	public Employee(String name, String designation, int sal) {
		this.name = name;
		this.designation = designation;
		this.sal = sal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getName() {
		return name;
	}

	public String getDesignation() {
		return designation;
	}

	public int getSal() {
		return sal;
	}

}
