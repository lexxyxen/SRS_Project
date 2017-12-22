package com.finalproject_0630926.Aleksandar_Lichkov;

import java.util.*;

public class SRSTester {
	
	public static Faculty faculty; 
	public static CourseCatalog courseCatalog; 
	public static ScheduleOfClasses scheduleOfClasses;
	static Student student = null;
	public static void main(String[] args) {
		SRSDataAccess srs = new SRSDataAccess();
		try {

			courseCatalog = srs.initializeCourseCatalog();
			scheduleOfClasses = srs.initializeScheduleOfClasses("2005");
			faculty = srs.initializeFaculty();
		}
		catch (InitializationException e) {
			System.out.println("An error has occured  " + e.getMessage());
			System.exit(0);	
		}

		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter your SSN: ");
		
		String ssn = sc.nextLine().trim();
		
		try {
			student = srs.initializeStudent(ssn);
		}
		catch (InvalidStudentException e) {
			System.out.println("Invalid SSN" );
			System.exit(0);	
		}

		
     


		try {
			srs.persistStudent(student);  
		}
		catch (InvalidStudentException e) {
			System.out.println("An error has occured  " + e.getMessage());
			System.exit(0);	
		}
		
		
		int choice;
//		   do{	
			  
			   Scanner sc2 = new Scanner(System.in);
			   student.display();

			   mainMenu();
			   choice=sc2.nextInt();
			   
			   switch(choice){
			   case 1:
		       {
		    	   clearScreen();
		    	   displaySchedule();
		          break;
		        }
		       case 2:
		       {
                   //Section sec = scheduleOfClasses.findSection("OBJ101 - 1");
		    	   registerCourse("OBJ101 - 1");
		           break;
		       }
		       case 3:
		       { 
		    	    clearScreen();
		    	    displayInstructor();
		    	   	sc.nextLine();
		            break;
		       }
		       case 4:
		       { 
		    	   System.exit(0);
		            break;
		       }
		       default:
		       {
		           System.out.println("Error :Your selection is out of range!");
		           break;
		       }

			   }
	    	
//		 }while(choice!=5);
		

	}
	
	public static void displaySchedule() {
		System.out.println("----------- Choose Schedule -------------");
		System.out.println();
		scheduleOfClasses.display();
		
	}
	
	public static void displayInstructor() {
		System.out.println("------------ Instructor's Information ------------");
		System.out.println();
		faculty.display();
	}
	
	public static void mainMenu() {
		System.out.println("Options");
		System.out.println("\t\t 1- Choose schedule of classee");
		System.out.println("\t\t 2- Add and save my schedule");
		System.out.println("\t\t 3- View Instructors");
		System.out.println("\t\t 4- log off");
		System.out.println("\t\t Your choice: ");

	}
	
	public static void registerCourse(String course) {
				Section sec = scheduleOfClasses.findSection(course);

				sec.enroll(student);
				System.out.println("Your have successfully added a new course.:");
				System.out.println();
				student.display();
	}
	// Clears the console screen
	public static void clearScreen()
	{
	    for(int i=0; i<200; i++)
	    {
	        System.out.println("\n");
	    }
	}
	
}
