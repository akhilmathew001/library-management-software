package database_1;
import java.sql.*;
public class MySqlcon {
	public static void main(String args[]){
		Connection con = null;
		Statement stmt;
		ResultSet rs;
		try{  
		Class.forName("com.mysql.jdbc.Driver");  	  
     	con=DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/sonoo","root","secret");  
		//here sonoo is database name, root is user name and password  
	  
		stmt=con.createStatement();  
	  
     	rs=stmt.executeQuery("select * from emp");  
		  
		while(rs.next())  {
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"     "+rs.getInt(3));  
		  
		
		} 
		}
		catch(Exception e)
		{ System.out.println(e);
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		  
		}  
	} 

