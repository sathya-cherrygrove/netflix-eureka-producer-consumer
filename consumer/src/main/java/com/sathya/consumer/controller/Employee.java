package com.sathya.consumer.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Employee {

	private String name;
	private String designation;
	private int sal;

	@JsonIgnoreProperties(ignoreUnknown = true)
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
	
	@Override
	public String toString()
	{
		return "Name: "+ name + " Designation: " + designation+ " Salary: "+ sal;
	}

}

