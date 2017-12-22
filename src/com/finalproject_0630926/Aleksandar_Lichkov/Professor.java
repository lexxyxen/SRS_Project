package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.ArrayList;

public class Professor extends Person {
	//Properties

	private String title;
	private String department;
	private ArrayList<Section> teaches; 

	// Constructor
	public Professor(String name, String ssn, String title, String dept) {
		// Invoking parent's constructor 
		super(name, ssn);
		
		setTitle(title);
		setDepartment(dept);

		teaches = new ArrayList<Section>();
	}
	
	//getters and setters
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}


	public void display() {
		// Invoking the person's display method
		super.display();
		System.out.println("Title:  " + getTitle());
		System.out.println("Corresponding Department:  " + getDepartment());
		displayTeachingAssignments();
	}
	
	public String toString() {
		return getName() + " (" + getTitle() + ", " +
		       getDepartment() + ")";
	}

	public void displayTeachingAssignments() {
		System.out.println("Teaching Assignments for " + getName() + ":");
		
	
		if (teaches.size() == 0) {
			System.out.println("None");
		}

		else for (Section s : teaches) {
			System.out.println("Course NO:  " +s.getRepresentedCourse().getCourseNo());
			System.out.println("Section NO:  " + s.getSectionNo());
			System.out.println("Course Name:  " + s.getRepresentedCourse().getCourseName());
			System.out.println("Day and Time:  " + s.getDayOfWeek() + " - " + s.getTimeOfDay());
			System.out.println();
		}
	}
	
	public void agreeToTeach(Section sec) {
		teaches.add(sec);
		sec.setInstructor(this);
	}
}
