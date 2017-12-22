package com.finalproject_0630926.Aleksandar_Lichkov;

import java.io.*;
import java.util.*;

public class SRSDataAccess {
    // properties 
	private String scheduleFileName;
	private String facultyFileName;
	private String assignmentsFileName; 
	private String courseFileName;
	private String prereqFileName;

	//Constructor
	public SRSDataAccess() {
		facultyFileName = "/Users/allyx/Documents/lasalle_courses/Java/SRSDatFiles/Faculty.dat";
		assignmentsFileName = "/Users/allyx/Documents/lasalle_courses/Java/SRSDatFiles/TeachingAssignments.dat";
		courseFileName = "/Users/allyx/Documents/lasalle_courses/Java/SRSDatFiles/CourseCatalog.dat";
		prereqFileName = "/Users/allyx/Documents/lasalle_courses/Java/SRSDatFiles/Prerequisites.dat";
		scheduleFileName = "/Users/allyx/Documents/lasalle_courses/Java/SRSDatFiles/SoC_SP2005.dat";
	}


	public Faculty initializeFaculty() throws InitializationException {
		Faculty faculty = new Faculty();
		String line = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(facultyFileName));

		    	line = br.readLine();
		    	while (line != null) {

				StringTokenizer sTok = new StringTokenizer(line, "\t");

				if (sTok.countTokens() != 4) {
					throw new InitializationException("File format error on record " + facultyFileName);
				}
				else {
					String name = sTok.nextToken();
					String ssn = sTok.nextToken();
					String title = sTok.nextToken();
					String dept = sTok.nextToken();

					Professor p = new Professor(name, ssn, title, dept);
					faculty.addProfessor(p);
				}
				
		    		line = br.readLine();
			}

			br.close();
		}
		catch (IOException e) { 
			throw new InitializationException("Error accessing " + facultyFileName + " file");
		}

		try { 
			br = new BufferedReader(
			    new FileReader(assignmentsFileName));

			line = br.readLine();
			while (line != null) {
			
				StringTokenizer st = new StringTokenizer(
					line, "\t");

				// If there aren't two columns, signal an error.

				if (st.countTokens() != 2) {
					throw new InitializationException("File format error on: " + assignmentsFileName + " file");
				}
				else {
					String ssn = st.nextToken();

					String fullSectionNo = st.nextToken();

					Professor p = faculty.findProfessor(ssn); 
					Section s = SRSTester.scheduleOfClasses.findSection(fullSectionNo); 

					if (p != null && s != null) p.agreeToTeach(s);
				}

				line = br.readLine();
			}

			br.close();
		}
		catch (IOException e) { 
			throw new InitializationException("Error accessing the " + assignmentsFileName + "file");
		}

		if (faculty.isEmpty()) {
			throw new InitializationException("Error initializing faculty's file ");
		}

		return faculty;
	}
	
		public  CourseCatalog initializeCourseCatalog() throws InitializationException {
		CourseCatalog catalog = new CourseCatalog();
		String line = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(courseFileName));

		    	line = br.readLine();
		    	while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");

				if (st.countTokens() != 3) {
					throw new InitializationException("File format on the " + courseFileName + " file");
				}
				else {
					String courseNo = st.nextToken();
					String courseName = st.nextToken();
					String creditValue = st.nextToken();
					double credits = Double.parseDouble(creditValue);
					
					Course c = new Course(courseNo, courseName, credits);
					catalog.addCourse(c);
				}
				
		    		line = br.readLine();
			}

			br.close();
		}
		catch (IOException e) { 
			throw new InitializationException("Error accessing the " + courseFileName + " file.");
		}


		if (catalog.isEmpty()) {
			throw new InitializationException("Error initializing cours catalog's information");
		}
		return catalog;
	}
	public ScheduleOfClasses initializeScheduleOfClasses(String semester) 
	    throws InitializationException {
		ScheduleOfClasses soc = new ScheduleOfClasses(semester);
		String line = null;
		BufferedReader br = null;

		try {
			br= new BufferedReader(new FileReader(scheduleFileName));

		    	line = br.readLine();
		    	while (line != null) {
				
				StringTokenizer st = new StringTokenizer(line, "\t");

				if (st.countTokens() != 6) {
					throw new InitializationException("File format error on " + scheduleFileName + " file.");
				}
				else {
					String courseNo = st.nextToken();


					String sectionNumber = st.nextToken();
					int sectionNo = Integer.parseInt(sectionNumber);

					String dayOfWeek = st.nextToken();
					String timeOfDay = st.nextToken();
					String room = st.nextToken();

					String capacityValue = st.nextToken();
					int capacity = Integer.parseInt(capacityValue);

					Course c = SRSTester.courseCatalog.findCourse(courseNo);

					Section s = new Section(
						sectionNo, dayOfWeek.charAt(0), 
						timeOfDay, c, room, capacity);

					String key = courseNo + " - " + s.getSectionNo();
					soc.addSection(s);

					c.addSection(s);
				}
				
		    		line = br.readLine();
			}

			br.close();
		}
		catch (IOException r) { 
			throw new InitializationException("Error accessing the" + scheduleFileName + " files");
		}

		if (soc.isEmpty()) {
			throw new InitializationException("Cannot initilize the schedule of classes");
		}

		return soc;
	}

	public Student initializeStudent(String sId) throws InvalidStudentException {
		
		Student s = new Student("###-##-####");

		String line = null;
		BufferedReader br = null;

		String pathToFile = "/Users/allyx/Documents/lasalle_courses/Java/SRS_project/src/" + sId + ".dat";

		try {
			
			br = new BufferedReader(new FileReader(pathToFile));

		    	line = br.readLine();
			if (line == null) {
				throw new InvalidStudentException("Invalid format on the " + pathToFile + " file.");
			}

			StringTokenizer st = new StringTokenizer(line, "\t");

			if (st.countTokens() != 4) {
				throw new InvalidStudentException("Invalid format on the " + pathToFile + " file.");
			}

			s.setSsn(st.nextToken());
			s.setName(st.nextToken());
			s.setMajor(st.nextToken());
			s.setDegree(st.nextToken());

		    	line = br.readLine();
		    	while (line != null) {
		
				String fullSectionNo = line.trim();
				Section sec = SRSTester.scheduleOfClasses.findSection(fullSectionNo);
				
				if (sec != null) 
				{
					sec.enroll(s);
				}

		    		line = br.readLine();
			}

			br.close();
		}
		catch (IOException e) {
			throw new InvalidStudentException("Error accessing the " + pathToFile + " file.");
		}

		
		return s;
	}

	public void persistStudent(Student s) throws InvalidStudentException {
		FileOutputStream fos = null;
		PrintWriter pw = null;
		String pathToFile = s.getSsn() + ".dat";

		try {
		
			fos = new FileOutputStream(pathToFile);
			pw = new PrintWriter(fos);


			pw.println(s.getSsn() + "\t" + s.getName() + "\t" + s.getMajor() + "\t" + s.getDegree());

			Collection<Section> sections = s.getEnrolledSections();
			for (Section sec : sections) {
				pw.println(sec.getFullSectionNo());
			}

			pw.close();
		}
		catch (IOException e) {			
			throw new InvalidStudentException("Error saving student to the " + pathToFile + " file.");
		}
	}
}
