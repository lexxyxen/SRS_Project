package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.HashMap;

public class CourseCatalog {
	//property as a hashmap
	private HashMap<String, Course> courses;

	//Constructor
	public CourseCatalog() {
		courses = new HashMap<String, Course>();
	}

	//Class method
	public void display() {
		for (Course c : courses.values()) {
			c.display();
			System.out.println();
		}
	}

	public void addCourse(Course c) {

		String cKey = c.getCourseNo();
		courses.put(cKey, c);
	}

	public Course findCourse(String courseNo) {
		return courses.get(courseNo);
	}

	public boolean isEmpty() {
		if (courses.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
