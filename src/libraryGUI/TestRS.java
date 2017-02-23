package libraryGUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestRS {
	static String url="jdbc:mysql://localhost:3306/library_books";
	static String user="root";
	static String password="secret";
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
	try {
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("Select * from shelf");
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet sheet = workbook.createSheet("lawix10");
	    HSSFRow rowhead = sheet.createRow((short) 0);
	    rowhead.createCell((short) 0).setCellValue("BOOK ID");
	    rowhead.createCell((short) 1).setCellValue("BOOK NAME");
	    rowhead.createCell((short) 2).setCellValue("AUTHOR NAME");
	    rowhead.createCell((short) 3).setCellValue("DEPARTMENT");
	    rowhead.createCell((short) 4).setCellValue("NO OF PIECES");
	    int i = 1;
	    while (rs.next()){
	        HSSFRow row = sheet.createRow((short) i);
	        row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(1)));
	        row.createCell((short) 1).setCellValue(rs.getString(2));
	        row.createCell((short) 2).setCellValue(rs.getString(3));
	        row.createCell((short) 3).setCellValue(rs.getString(4));
	        row.createCell((short) 4).setCellValue(rs.getInt(5));
	        i++;
	    }
	    String yemi = "g:/test.xls";
	    FileOutputStream fileOut = new FileOutputStream(yemi);
	    workbook.write(fileOut);
	    fileOut.close();
	    }catch (SQLException e2) {
	        e2.printStackTrace();
	    } catch (FileNotFoundException e3) {
	        e3.printStackTrace();
	    } catch (IOException e4) {
	        e4.printStackTrace();
	    }
	}
}
