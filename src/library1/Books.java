//representing variable in SQL query
package library1;

import java.sql.*;
import java.util.Scanner;

public class Books {
	Cybrarian obj = new Cybrarian();
	//ResultSet reset=obj.getResulset();
	Scanner scn = new Scanner(System.in);
	ResultSet rst=null;
	DBconnection db = new DBconnection();
	int NO=0,book_id,pieces;
	String book_name,author,department;
	void addBooks(){
		try{
		 db.setConnection();//through DBconnection
		 Statement stmnt=obj.getStatementobj();
		System.out.println("Enter here the book to be added to library");
		System.out.println("Enter the number of book you wish to add to the library database");
		NO=scn.nextInt();
		for(int i=0; i<NO;i++){
			System.out.println("Details of book "+(i+1));
			System.out.println("Enter details in the following order with space: book id book name author name department no of pieces");
			book_id=scn.nextInt();
			book_name=scn.next();
			author=scn.next();
			department=scn.next();
			pieces=scn.nextInt();
String sql="insert into shelf (book_id,book_name,author,department,pieces) values ('"+book_id+"','"+book_name+"','"+author+"','"+department+"','"+pieces+"')";
			try {
				stmnt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("Data loading.. please wait..");
				Thread.sleep(3000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	   }
		finally{
		System.out.println("Updated the library");
		db.dbClose();
		multiSearchHandler();
		}
	}
	void displayAllbooks(){
		String sql="select * from shelf";
		db.setConnection();
		Statement stmnt=obj.getStatementobj();
	    try {
			rst=stmnt.executeQuery(sql);
			System.out.println("BOOK ID         BOOK NAME         AUTHOR        DEPARTMENT        NO.OF PIECES");
			while(rst.next()){
				System.out.println(rst.getInt(1)+"       "+rst.getString(2)+"               "+rst.getString(3)+"               "+rst.getString(4)+"      "+rst.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    finally{
	    	db.dbClose();
	    	multiSearchHandler();
	    }
	  } 
	    void multiSearchHandler(){
	    	int flag=0;
	    	System.out.println("would you like to return to admin page, if yes press 1 otherwise 0");
			flag=scn.nextInt();
			if(flag==1){
				System.out.println("Redirecting");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Admin_Page go = new Admin_Page();
				go.redirectionHandler();
			}
			else{
				System.out.println("Returning to login page.....");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Login_Page returnto = new Login_Page();
				returnto.redirectionHandlerLogin();
			}
	     }		
}
