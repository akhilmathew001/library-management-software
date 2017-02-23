package library1;
import java.util.Scanner;
import java.sql.*;

public class BookHandling {
Statement stmt1,stmt2,stmt3,stmt4;//for allotting books
Statement stmt5,stmt6;//for returning books
ResultSet reset1,reset2;
public static String status1="in use",status2="returned";
public static int prnE=0,no_pieceCheck;
public static int bookID;
public static String book_nameFD;//FD:from database
String booksearch;
Cybrarian db = new Cybrarian();
Connection conn=null;
Scanner scn = new Scanner(System.in);
void allotBooks(){
	System.out.println("Enter the PRN of the student");
	prnE=scn.nextInt();
	System.out.println("Enter the name of the book to be issued....//for search purpose//");
	booksearch=scn.next();
	String sql1="select * from shelf where book_name='"+booksearch+"'";
	db.dBCaller();
	conn=db.getConn();
	try {
		conn.setAutoCommit(false);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		stmt1=conn.createStatement();
		stmt2=conn.createStatement();
		stmt3=conn.createStatement();
		stmt4=conn.createStatement();
	} catch (SQLException e1){
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		reset1=stmt1.executeQuery(sql1);
		System.out.println("BOOK ID       BOOK NAME         AUTHOR        DEPARTMENT       NO.OF PIECES");
		while(reset1.next()){
			System.out.println(reset1.getInt(1)+"          "+reset1.getString(2)+"              "+reset1.getString(3)+"             "+reset1.getString(4)+"         "+reset1.getInt(5));
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Enter the book id of the issuing book");
    bookID=scn.nextInt();
	try{
	    String sql2="select * from shelf where book_id='"+bookID+"'";
		reset2=stmt2.executeQuery(sql2);
		while(reset2.next()){
			book_nameFD=reset2.getString(2);
			no_pieceCheck=reset2.getInt(5);
			//System.out.println(book_nameFD);
		}
		if(no_pieceCheck==0){
			int flag=0;
			DBconnection dclose = new DBconnection();
			dclose.dbClose();
			System.out.println("No more books available...");
			System.out.println("If you wish to take another book , then press 1 otherwise 0");
			flag=scn.nextInt();
			if(flag==1){
			allotBooks();
		     }
			else{
				Admin_Page rediect = new Admin_Page();
				rediect.redirectionHandler();
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		String sql3="update student set book_inhand='"+book_nameFD+"',status='"+status1+"' where pnr='"+prnE+"'";
		stmt3.execute(sql3);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try{
      String sql4="update shelf set pieces=(pieces-1) where book_id='"+bookID+"'";
      stmt4.execute(sql4); 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Book issued....Redirecting");
		DBconnection dclose = new DBconnection();
		dclose.dbClose();
		Admin_Page rediect = new Admin_Page();
		rediect.redirectionHandler();
	}
}
 void returnBook(){
	 System.out.println("Enter the PRN of student ");
	 prnE=scn.nextInt();
	 System.out.println("Enter the ID of returning book");
	 bookID=scn.nextInt();
	 String sql5="update shelf set pieces=(pieces+1) where book_id='"+bookID+"'";
	 String sql6="update student set status='"+status2+"' where pnr='"+prnE+"'";
	 db.dBCaller();
	 conn=db.getConn();
	 try {
		stmt5=conn.createStatement();
		stmt6=conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 try {
		stmt5.execute(sql5);
		stmt6.execute(sql6);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		DBconnection dclose = new DBconnection();
		dclose.dbClose();
		System.out.println("Redirecting...");
		Admin_Page redirect = new Admin_Page();
		redirect.redirectionHandler();
	}
 }
}
