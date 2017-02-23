package library1;
import java.util.Scanner;
public class Login_Page {
	String student=null;
	String admin=null;
	String Login;
	Boolean status=true;
	Scanner scn = new Scanner(System.in);
	void caller_Method(){
		if(status){
		loginScreen();
		validateLogin();
		}
		else if(!status){
			status=true;
			validateLogin();
			
		}
	}
	void loginScreen(){
		System.out.println("Which mode you wish to login as: Student or Admin");
		Login=scn.next();
	}
	void validateLogin(){
		if((Login.equalsIgnoreCase("student"))){
			System.out.println("student page");
			Student_Page std = new Student_Page();
			std.studentValidation();
		}
		else if(Login.equalsIgnoreCase("admin")){
			System.out.println("admin page");
			Admin_Page admn = new Admin_Page();
			admn.validation();
		}
		else{
			Login_Page err = new Login_Page();
			System.out.println("Please enter valid option: student or admin");
			status=false;
			err.caller_Method();
		}
	}
	void redirectionHandlerLogin(){
		loginScreen();
		validateLogin();
	}
}
