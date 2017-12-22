package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.HashMap;
public class ScheduleOfClasses {

	//properties
	private String semester;

	private HashMap<String, Section> sectionsOffered; 

	 ScheduleOfClasses(String semester) {
		setSemester(semester);
		
		sectionsOffered = new HashMap<String, Section>();
	}

	 // getters and setters 
	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}

	//other methods
	public void display() {
		System.out.println();

		for (Section s : sectionsOffered.values()) {
			s.display();
			System.out.println();
		}
	}

	public void addSection(Section s) {
		

		String key = s.getRepresentedCourse().getCourseNo() + 
			     " - " + s.getSectionNo();
		sectionsOffered.put(key, s);


		s.setOfferedIn(this);
	}


	public Section findSection(String fullSectionNo) {
		return sectionsOffered.get(fullSectionNo);
	}

	public boolean isEmpty() {
		if (sectionsOffered.size() == 0) return true;
		else return false;
	}
}
