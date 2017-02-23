package libraryGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MySQLconfiguration {
	
	private static String mysqlusername;
	private static String mysqlpassword;
	private static int mysqlport;
	int option;
	public static int flag=2;
	JTextField user = new JTextField();
	JTextField pass = new JTextField();
	JTextField port = new JTextField();
	JTextField uu = new JTextField();
	Object[] message = {"PLEASE ENTER THE DETAILS OF YOUR MySQL TO CONNECT WITH YOUR DATABASE \n","Enter your MySQL username: ",user,
		"Enter your MySQL password: ",pass,"Enter your MySQL port number",port};

	public static void main(String[] args) {
		MySQLconfiguration obj = new MySQLconfiguration();
		obj.configuration();
	}
   void configuration(){
	   int noclose=0;
	option = JOptionPane.showConfirmDialog(null, message, "MySQL connection setup", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
	   if(option==JOptionPane.OK_OPTION){
		   if(user.getText().isEmpty()){
			   JOptionPane.showMessageDialog(null, "Please fill all the fields");
			   noclose=1;
		   }
		   else if(pass.getText().isEmpty()){
			   JOptionPane.showMessageDialog(null, "Please fill all the fields");
			   noclose=1;
		   }
		   else if(port.getText().isEmpty()){
			   JOptionPane.showMessageDialog(null, "Please fill all the fields");
			   noclose=1;
		   }
		   else{
		   mysqlusername=user.getText();
		   mysqlpassword=pass.getText();
		   mysqlport=Integer.valueOf(port.getText());
			   System.out.println(mysqlusername+mysqlpassword+mysqlport);
			   shelfdbcheck();
		   }
		   if(noclose==1){
			   MainWindow.main(null);
		   }
		   
	   }
	   else{
		   JOptionPane.showMessageDialog(null, "You can continue only if you complete this step");
		   MainWindow.main(null);
	   }
   }
   
  static String getuser(){
	   return mysqlusername;
   }
  static  String getpass(){
	   return mysqlpassword;
   }
  static  int getport(){
	   return mysqlport;
   }
  
  void shelfdbcheck(){
		Connection con = null,conn=null;
		Statement stmt1=null,stmt2=null,stmt3=null,stmt4=null;
		String url="jdbc:mysql://localhost:"+mysqlport+"/";
		String user=mysqlusername;
		String pass=mysqlpassword;
		String sql1="create database library_books";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			stmt1=con.createStatement();
			stmt1.execute(sql1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			if(e.getErrorCode()==1007){
				System.out.println("database already exist");
				flag=1;
				loginpagecall();
			}
	   }
	   finally{
		   try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   }
	if(flag==2){
			Object[] message = {
	"Databases required for the operation of the application are not found in your system. Would you like to configure it?"
				};
				Object[] options = {"Yes, Auto configure it",
		                "Manually configure"};
option = JOptionPane.showOptionDialog(null,message,"Database not found!",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,
		         options[1]);
	 if (option == JOptionPane.OK_OPTION){
				try {
					String url2="jdbc:mysql://localhost:"+mysqlport+"/library_books";
					String sql2 = "create table shelf (book_id int(5),book_name varchar(255), author varchar(255), department varchar(255), pieces int(10))"; 
					String sql3="create table student (pnr int(5), name varchar(255), book_inhand varchar(255), status varchar(20))";
					String sql4="create table admindb(name varchar(255),pass varchar(255))";
					Class.forName("com.mysql.jdbc.Driver");
					conn=DriverManager.getConnection(url2,mysqlusername,mysqlpassword);
					stmt2=conn.createStatement();
					stmt3=conn.createStatement();
					stmt4=conn.createStatement();
					stmt2.execute(sql2);
					stmt3.execute(sql3);
					stmt4.execute(sql4);
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				finally{
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			oneeachdbcheck();
		}
		 else{
			 ManualConfig.main(null);
		 }
	  }
}
	
void oneeachdbcheck(){
		 Connection con = null;
			Statement stmt1=null;
			String url="jdbc:mysql://localhost:"+mysqlport+"/";
			String sql1="create database if not exists oneeachdb";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,mysqlusername,mysqlpassword);
				stmt1=con.createStatement();
				stmt1.execute(sql1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	 JOptionPane.showMessageDialog(null, "Required databases are created. Application is ready to be used");
	 loginpagecall();
	}
	 
void loginpagecall(){
	MainWindow obj = new MainWindow();
	obj.closemainwindow();
	LoginPage.main(null);
	 }
}
