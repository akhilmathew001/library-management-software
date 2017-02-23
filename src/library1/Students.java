package library1;

import java.sql.*;   
import java.util.Scanner;
import java.io.*;

public class Students {
	Scanner scn = new Scanner(System.in);
	int pnr,no_students;
	String name,bookinuse;
	String status1="in use";
	String status2="returned";
	void addStudent(){
	  try{
	  String book="";
	  String stat="";//just to specify in query
	  Cybrarian db = new Cybrarian();
	  db.dBCaller();//through cybrosys
	  Statement stmnt=db.getStatementobj();
	  System.out.println("Welcome to student database");
	  System.out.println("How much students you want to add or update to the database");
	  no_students=scn.nextInt();
	  for(int i=0;i<no_students;i++){
		System.out.println("Details of "+(i+1)+" student");
		System.out.println("Enter the student details in the following order PNR and NAME");
		pnr=scn.nextInt();
		name=scn.next();
		String sql="insert into student (pnr,name,book_inhand,status) values ('"+pnr+"','"+name+"','"+book+"','"+stat+"')";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   }
	  }
		finally{
			System.out.println("Updated student database");
			DBconnection dclose = new DBconnection();
			dclose.dbClose();
			System.out.println("Redirecting...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Admin_Page retun = new Admin_Page();
			retun.redirectionHandler();
		}
	}
  void searchStudentDetails(){
	  int prnE;
	  String book_inhand=null,status=null;
	  System.out.println("Enter the PRN number");
	  prnE=scn.nextInt();
	  String sql="select * from student where pnr='"+prnE+"'";
	  Cybrarian db = new Cybrarian();
	  ResultSet reset=null;
	  db.dBCaller();
	  Statement stmt=db.getStatementobj();
	  try {
		reset=stmt.executeQuery(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		while(reset.next()){
			 book_inhand=reset.getString(3);
			 status=reset.getString(4);
		 }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		DBconnection dclose = new DBconnection();
		dclose.dbClose();
		if(status1.equalsIgnoreCase(status)){
			 System.out.println("The student has currently have a book named: "+book_inhand+" in his/her hand");
		 }
		 else if(status2.equalsIgnoreCase(status)){
			 System.out.println("The student currently not using any books");
		}
		 else if(status.equalsIgnoreCase("")){
			 System.out.println("The student currently not using any books");
		 }
		System.out.println("Do you wish to search another student, if so press 1 otherwise press0... to exit press 2");
		int flag=scn.nextInt();
		if(flag==1){
			searchStudentDetails();
		}
		else if(flag==0){
			Admin_Page re = new Admin_Page();
			re.redirectionHandler();
		}
		else if(flag==2)
		{
			Login_Page re = new Login_Page();
			re.redirectionHandlerLogin();
		}
	 }
  }
  void printStudentDetails(){
	    int prnE=0;
	    ResultSet reset=null;
	    Statement stmt;
	    String nameD=null,bookD=null,statusD=null;
		System.out.println("Enter the PRN of the student whose details is to be printed");
		prnE=scn.nextInt();
		String sql="select * from student where pnr='"+prnE+"'";
		Cybrarian db = new Cybrarian();
	    db.dBCaller();
	    stmt=db.getStatementobj();
		try {
			reset=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		while(reset.next()){
			nameD=reset.getString(2);
			bookD=reset.getString(3);
			statusD=reset.getString(4);
		}
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			DBconnection dclose = new DBconnection();
			dclose.dbClose();
			System.out.println("Details printed.. Check it for on the destination folder");
		}
		try {
			File file = new File("'"+nameD+"'.doc");
			FileWriter fout = new FileWriter(file);
			fout.write("Name of student: "+nameD+" \nBook in hand: "+bookD+" \nstatus of book: "+statusD);
			fout.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Do you wish to print another students details, if so press 1 otherwise press0... to exit press 2");
		int flag=scn.nextInt();
		if(flag==1){
			printStudentDetails();
		}
		else if(flag==0){
			Admin_Page re = new Admin_Page();
			re.redirectionHandler();
		}
		else if(flag==2)
		{
			Login_Page re = new Login_Page();
			re.redirectionHandlerLogin();
		}
	}
}
