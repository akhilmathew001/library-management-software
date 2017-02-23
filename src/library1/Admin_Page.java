package library1;
import java.util.Scanner;
public class Admin_Page {
    Scanner scn = new Scanner(System.in);
	int Login_key,count=0,admn_op;
	void validation(){
		System.out.println("Enter the administrator password");
		Login_key=scn.nextInt();
		if(Login_key==1010){
			System.out.println("logged in as admin");
			selection();
		}
		else{
			System.out.println("Password is not correct. Please enter valid one");
			count++;
			if(count<5){
			   System.out.println("You have "+(5-count)+" attempts remaining");
			   validation();}
			else{
				Login_Page direct = new Login_Page();
				System.out.println("you have entered an incorrect password 5 times. Please login after some times");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				direct.caller_Method();
			}
		   
		}
		
	}
	void redirectionHandler(){
		selection();
	}
  void selection(){
	System.out.println("what operation would you like to do");
	System.out.println(" 1:Add books to library \n 2:Manage student database \n 3:View all books \n 4:search student detail " +
			"\n 5:Allot books to student \n 6:Return books \n 7:Print student details \n 8:Exit");
	admn_op=scn.nextInt();
	if(admn_op==1){
		Books add = new Books();
		add.addBooks();
	}
	else if(admn_op==2){
		Students add = new Students();
		add.addStudent();
	}
	else if(admn_op==3){
		Books display = new Books();
		display.displayAllbooks();
	}
	else if(admn_op==4){
		Students search = new Students();
		search.searchStudentDetails();
	}
	else if(admn_op==5){
		BookHandling allot = new BookHandling();
		allot.allotBooks();
	}
	else if(admn_op==6){
		BookHandling returnB = new BookHandling();
		returnB.returnBook();
	}
	else if(admn_op==7){
		Students print = new Students();
		print.printStudentDetails();
	}
	else if(admn_op==8){
		Login_Page exit = new Login_Page();
		exit.redirectionHandlerLogin();
	}
	else{
		System.out.println("Option do not match... Retry once again");
		selection();
	}
}
}
