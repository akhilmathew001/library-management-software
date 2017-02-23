package database_1;

import java.awt.Desktop; 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	
	public static void main(String args[]) {
		int prn=3306;
		Path path=Paths.get("C:/i-LibraryDetails");
		if (Files.exists(path,LinkOption.NOFOLLOW_LINKS)) {
		   System.out.println("folder exist");
		}
		else{
		File folder = new File("C:/i-LibraryDetails");
		folder.mkdir();
		}
		try {
			File file = new File("C:/i-LibraryDetails/Readme.txt");
			Desktop.getDesktop().edit(file);///To open a file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:"+prn+"/library_books";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,"root","secret");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from shelf");
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
