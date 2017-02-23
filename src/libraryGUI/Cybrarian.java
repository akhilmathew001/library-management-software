//Very important// 
//Its better to open and close the database quickly rather than keeping the database open throughout the program//
package libraryGUI;
import java.sql.*;

class DBconnection{
	static Connection con;
	static Statement stmt;
	String url;
	static String muser,mpass;
	static int mport;
	void setConnection(){
		muser=MySQLconfiguration.getuser();
		mpass=MySQLconfiguration.getpass();
		mport=MySQLconfiguration.getport();
		url="jdbc:mysql://localhost:"+mport+"/library_books";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,muser,mpass);
			stmt=con.createStatement();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}
	void dbClose(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("******************");
	}
}
public class Cybrarian {
	String user,pass;
	int port;
	Connection conDB;
	Connection getConn(){
	   Connection obj=DBconnection.con;
	   return obj;
	}
	Statement getStatementobj(){
		Statement obj=DBconnection.stmt;
		return obj;
	}
	public void dBCaller(){
		DBconnection load = new DBconnection();
		load.setConnection();
	}
    
	Connection OneEachDBOP(){
		port=MySQLconfiguration.getport();
		user=MySQLconfiguration.getuser();
		pass=MySQLconfiguration.getpass();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conDB=DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/OneEachDB",user,pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conDB;
	}
	void closeEachDB(){
		try {
			conDB.close();
			System.out.println("###############");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
