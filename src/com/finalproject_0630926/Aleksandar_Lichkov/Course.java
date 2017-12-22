package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.ArrayList;
import java.util.Collection;

public class Course {
	//properties
	private String courseNo;
	private String courseName;
	private double credits;
	private ArrayList<Section> offeredAsSection; 
	private ArrayList<Course> prerequisites; 
	
	// Constructor
	public Course(String courseNo, String courseName, double credits) {
		setCourseNo(courseNo);
		setCourseName(courseName);
		setCredits(credits);
		
		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}
	
	//getters and setters 
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public double getCredits() {
		return credits;
	}
	
	//Class methods
	public void display() {
		System.out.println(getCourseNo() );
		System.out.println("Course Name:  " + getCourseName());
		System.out.println("Credits:  " + getCredits());
		System.out.println("Prerequisites: ");

		for (Course c : prerequisites) {
			System.out.println(c.toString());
		}


		System.out.print("In Section(s):  ");
		for (Section sec : offeredAsSection) {
			System.out.print(sec.getSectionNo() + " ");
		}
		System.out.println();
	}
	
	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	public void addPrerequisite(Course course) {
		prerequisites.add(course);
	}

	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) {
			return true;
		}
		else{
			return false;
		} 
	}

	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(char day, String time, String room, int capacity) {
		
		Section sec = new Section(offeredAsSection.size() + 1 ,day, time, this, room, capacity);

		addSection(sec);
		
		return sec;
	}

	public void addSection(Section section) {
		offeredAsSection.add(section);
	}
}
