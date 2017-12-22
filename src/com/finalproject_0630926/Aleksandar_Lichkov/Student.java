package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.ArrayList;
import java.util.Collection;

public class Student extends Person {
	//properties
	private String major;
	private String degree;
	private ArrayList<Section> attends; 
	
	// Constructors

	public Student(String name, String ssn, String major, String degree) {
		// invoking parent's constructor.
		super(name, ssn);

		this.setMajor(major);
		this.setDegree(degree);

		attends = new ArrayList<Section>();
	}
	
	public Student(String ssn) {
		this("AAAA", ssn, "AAAA", "AAAA");
	}

	//getters and setters
	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	// class methods
	public void display() {
		// invoking Person's display method.

		super.display();
		
		System.out.println("Major:  " + this.getMajor());
		System.out.println("Degree:  " + this.getDegree());
		this.displayCourseSchedule();
		System.out.println();
	}	
	
	public String toString() {
		return this.getName() + " " + this.getSsn() + " " + this.getDegree() + " " + this.getMajor() ;
	}

	public void displayCourseSchedule() {

		System.out.println("Course Schedule for " + this.getName());
		
		for (Section s : attends) {
		  {
			System.out.println("Course NO:  " + s.getRepresentedCourse().getCourseNo());
			System.out.println("Section NO:  " + s.getSectionNo());
			System.out.println("Course Name:  " + s.getRepresentedCourse().getCourseName());
			System.out.println("Day and Time:  "  + s.getDayOfWeek() + " - " + s.getTimeOfDay());
			System.out.println("Instructor: " + s.getInstructor().getName());
			System.out.println();
		    }
		}
	}
	
	public void addSection(Section s) {
		attends.add(s);
	}
	
	public void dropSection(Section s) {
		attends.remove(s);
	}
	
	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)) {
			return true;
		}
		else {
			return false;
		}
	}
		
	public Collection<Section> getEnrolledSections() {
		return attends;
	}
}
