package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.HashMap;


public class Faculty {

	//hashMap as a property
	private HashMap<String, Professor> professors;


	//Constructor
	public Faculty() {
		professors = new HashMap<String, Professor>();
	}

	//Class methods
	public void display() {
		for (Professor p : professors.values()) {
			p.display();
			System.out.println();
		}
	}

	public void addProfessor(Professor p) {
		professors.put(p.getSsn(), p);
	}

	public Professor findProfessor(String ssn) {
		return (Professor) professors.get(ssn);
	}

	public boolean isEmpty() {
		if (professors.size() == 0) {
			return true;
			}
		else {
			return false;
		}
	}
}
