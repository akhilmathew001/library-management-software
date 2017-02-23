package library1;
import java.sql.*; 
import java.util.*;
public class Student_Page {
public int operation;
Scanner scn = new Scanner(System.in);
static Cybrarian conn = new Cybrarian();
 ///public static Statement stmnt=conn.getStatementobj();
public static ResultSet reset;
Statement stmt1;
public static ResultSet result=null;
int pnrE,row_no;
String nameE;
static int pnrD[]=new int[50];
static String nameD[]=new String[100];
int count=0,flag=0;
String sql="select pnr,name from student";
 void studentValidation(){
	 System.out.println("Enter your PNR ");
	 pnrE=scn.nextInt();
	 System.out.println("Enter your NAME");
	 nameE=scn.next();
	 conn.dBCaller();//through cybrosys
	 stmt1=conn.getStatementobj();
	 try {
		result=stmt1.executeQuery(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		int i=0;
			while(result.next()){
			  //int i=0;
			  pnrD[i]=result.getInt(1);
			  nameD[i]=result.getString(2);
			  if(pnrD[i]==pnrE&&nameD[i].equalsIgnoreCase(nameE)){
					flag=flag+1;
				}
			  i++;
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		DBconnection dclose = new DBconnection();
		dclose.dbClose();
	}
	
	if(flag>0){
		System.out.println("Authentication validated");
		System.out.println("Welcome "+nameE+"....");
		callerStudent();
	}
	else{
		System.out.println("Entered details donot matching. If you are a valid user enter correct details");
		System.out.println("If you do not have account, contact your library admin");
		count=count+1;
		if(count<5){
			System.out.println("You have "+(5-count)+" attempt remaining");
			studentValidation();
		}
		else{
			System.out.println("Number of attempts over... Redirecting to Login page");
			Login_Page re = new Login_Page();
			re.redirectionHandlerLogin();
		}
	} 
 }
 void callerStudent(){
	 menu();
	 directTheOperation();
 }
 void menu(){
	 System.out.println("Welcome to iLibrary: Cybrary");
	 System.out.println("Explore the library... We have a very good collection of books for all departments");
	 try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 System.out.println("How you want to search your desired book");
	 System.out.println("1:Search by Name of the book");
	 System.out.println("2:Search by Author name");
	 System.out.println("3:Search by Department");
	 operation=scn.nextInt();
 }
 void directTheOperation(){
	 Student_Page direct = new Student_Page();
	 if(operation==1){
		System.out.println("search by name");
		direct.searchByname();
	 }
	 else if(operation==2){
		System.out.println("search by author"); 
		direct.searchByauthor();
	 }
	 else if(operation==3){
		System.out.println("search by deptmnt");
		direct.searchBydepartment();
	 }
	 else{
		 System.out.println("Error in selecction of search method. Select only available option");
		menu();
	 }
 }
 void multiSearcchHandler(){//inorder to avoid entering password two times
	 menu();
	 directTheOperation();
 }
 void searchByname(){
	 conn.dBCaller();
	 Statement stmnt=conn.getStatementobj();
	 String search_name;
	 System.out.println("Enter the name of the book you desired");
	 search_name=scn.next();
	 try {
		reset=stmnt.executeQuery("select * from shelf where book_name like '"+search_name+"'");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 result();
 }
 void searchByauthor(){
	 conn.dBCaller();
	 Statement stmnt=conn.getStatementobj();
	 String search_name;
	 System.out.println("Enter the author's name of the book you desired");
	 search_name=scn.next();
	 try {
		reset=stmnt.executeQuery("select * from shelf where author like '"+search_name+"'");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 result();
 }
 void searchBydepartment(){
	 conn.dBCaller();
	 Statement stmnt=conn.getStatementobj();
	 String search_name;
	 System.out.println("Enter the department");
	 search_name=scn.next();
	 try {
		reset=stmnt.executeQuery("select * from shelf where department like '"+search_name+"'");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 result();
 }
 void result(){
	 int no_data=0;
	 try {
		 if (!reset.next() ) {
			   no_data=1;
			}
		while(reset.next()){
			System.out.println("Book:"+reset.getString(2)+" by "+reset.getString(3)+" No of pieces: "+reset.getString(5));
		 }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		int flag=0;
	  DBconnection dClose = new DBconnection();
	  dClose.dbClose();
	  if(no_data==1){
		  System.out.println("No books found for your specified search.. Search with another keyword");
	  }
	  System.out.println("Do you wish to search again, if so press 1 otherwise 0...//Press 2 to exit");
	  flag=scn.nextInt();
	  if(flag==1){
		  Student_Page direct = new Student_Page();
		  direct.multiSearcchHandler();
	  }
	  else if(flag==0){
		 Login_Page go2login = new Login_Page();
		 go2login.redirectionHandlerLogin();
	  }
	  else{
		  System.exit(1000);
	  }
	}
  }
}
