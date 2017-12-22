package com.finalproject_0630926.Aleksandar_Lichkov;

public abstract class Person {
	
	//properties 
	private String name;
	private String ssn;
	
	//constructor
	public Person(String name, String ssn) {
		this.setName(name);
		this.setSsn(ssn);
	}


	// getters and settes
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getSsn() {
		return ssn;
	}

	

	public abstract String toString(); 

	public void display() {
		System.out.println("Name:  " + this.getName());
		System.out.println("SSN:   " + this.getSsn());
	}
}	
