package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.HashMap;

public class Section {
	//properties
	private int sectionNo;
	private char dayOfWeek;
	private String timeOfDay;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;

	private HashMap<String, Student> enrolledStudents; 
// Constructor 
	public Section(int sNo, char day, String time, Course course,
		       String room, int capacity) {
		setSectionNo(sNo);
		setDayOfWeek(day);
		setTimeOfDay(time);
		setRepresentedCourse(course);
	
		// Instructor has not yet been identified
		setInstructor(null);

		enrolledStudents = new HashMap<String, Student>();
	}
									

	//getters and setters 

	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}
	
	public int getSectionNo() {
		return sectionNo;
	}
	
	public void setDayOfWeek(char dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public char getDayOfWeek() {
		return dayOfWeek;
	}
		
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setInstructor(Professor prof) {
		instructor = prof;
	}
	
	public Professor getInstructor() {
		return instructor;
	}
	
	public void setRepresentedCourse(Course c) {
		representedCourse = c;
	}
	
	public Course getRepresentedCourse() {
		return representedCourse;
	}		



	public void setOfferedIn(ScheduleOfClasses soc) {
		offeredIn = soc;
	}

	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}	


	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " +
		       getSectionNo() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + 
		       " - " + getSectionNo();
	}
	
	public void enroll(Student s) {		
		enrolledStudents.put(s.getSsn(), s);
		s.addSection(this);

	}

	public boolean drop(Student s) {

		if (!s.isEnrolledIn(this)) return false;
		else {
			
			enrolledStudents.remove(s.getSsn());

			s.dropSection(this);
			return true;
		}
	}

	public int getTotalEnrollment() {
		return enrolledStudents.size();
	}	

	public void display() {
		System.out.println(getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " 
	+  getDayOfWeek() + " - " + getTimeOfDay());
		System.out.println("Offered date:  " + getDayOfWeek() + " at " + getTimeOfDay());
	}
	
	public void displayStudentRoster() {
		System.out.print(getTotalEnrollment() + "students enrolled");
		for (Student s : enrolledStudents.values()) {
			System.out.println(s.getName());
		}
	}
	
	public boolean isSectionOf(Course c) {
		if (c == representedCourse) return true;
		else return false;
	}
}
