//Very important//
//Its better to open and close the database quickly rather than keeping the database open throughout the program//
package library1;
import java.sql.*;

class DBconnection{
	static Connection con;
	static Statement stmt;
	//static ResultSet rs;
	String url="jdbc:mysql://localhost:3306/library_books";
	String user="root";
	String password="secret";
	void setConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,password);
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

}
