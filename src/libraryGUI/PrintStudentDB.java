package libraryGUI;

import java.awt.EventQueue;      
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.awt.Color;

@SuppressWarnings("serial")
public class PrintStudentDB extends JFrame {
	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;
	
	int roW;
	String bookNAME[],authNAME[],department[];
	int bookID[],nopiece[];
	Object selectedRow;
	Object studentname;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintStudentDB frame = new PrintStudentDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public PrintStudentDB(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();
		int mysqlport=MySQLconfiguration.getport();
		Connection con = null;
		ResultSet rs1=null;
		Statement st = null;
        String sql="select pnr,name from student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
			st=con.createStatement();
	        rs1 = st.executeQuery(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("DETAILS OF BOOK");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
		);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 490, 27);
		panel.add(menuBar);
		
		JMenu mainMenu = new JMenu("File");
		mainMenu.setBackground(new Color(0, 0, 204));
		menuBar.add(mainMenu);
		
		JMenuItem printMenu = new JMenuItem("Print");
		printMenu.setIcon(new ImageIcon("C:\\iLibrary images\\Print-Icon.png"));
		printMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				printDATA();
			}
		});
		mainMenu.add(printMenu);
		
		JMenuItem Refreshmenu = new JMenuItem("Refresh");
		Refreshmenu.setIcon(new ImageIcon("C:\\iLibrary images\\reload.png"));
		mainMenu.add(Refreshmenu);
		
		JMenuItem exitMenu = new JMenuItem("Exit");
		exitMenu.setIcon(new ImageIcon("C:\\iLibrary images\\DeleteRed.png"));
		exitMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		mainMenu.add(exitMenu);
		Refreshmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			   refreshmenu();
			}
		});
		
		JMenu mnNewMenu = new JMenu("Edit");
		menuBar.add(mnNewMenu);
		
		JMenuItem Updatemenu = new JMenuItem("Update student");
		Updatemenu.setIcon(new ImageIcon("C:\\iLibrary images\\Update-icon.png"));
		mnNewMenu.add(Updatemenu);
		Updatemenu.addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updatemenu();
			}
			
		});
		
		JMenuItem Deletemenu = new JMenuItem("Delete student");
		Deletemenu.setIcon(new ImageIcon("C:\\iLibrary images\\Delete_Icon.png"));
		mnNewMenu.add(Deletemenu);
		Deletemenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deletemenu();
			}
		});
		
		scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 31, 480, 391);
		panel.add(scrollPane);
		table = new JTable();
		table.setModel(DbUtils.resultSetToTableModel(rs1));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            selectedRow=table.getValueAt(table.getSelectedRow(),0);
	            studentname=table.getValueAt(table.getSelectedRow(), 1);
	        }
	    });
		
	}
	@SuppressWarnings("deprecation")
	void printDATA(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();
		int mysqlport=MySQLconfiguration.getport();
		Connection con = null;
		ResultSet rs=null;
		Statement st = null;
        String sql="select pnr,name from student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
			st=con.createStatement();
	        rs = st.executeQuery(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		    HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet("Student database");
		    HSSFRow rowhead = sheet.createRow((short) 0);
		    rowhead.createCell((short) 0).setCellValue("PNR");
		    rowhead.createCell((short) 1).setCellValue("STUDENT NAME");
		    int i = 1;
		    try {
				while (rs.next()){
				    HSSFRow row = sheet.createRow((short) i);
				    row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(1)));
				    row.createCell((short) 1).setCellValue(rs.getString(2));
				    i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String printdetail = "C:/i-LibraryDetailsAdmin/Studentsdb.xls";
		    try{
		    FileOutputStream fileOut = new FileOutputStream(printdetail);
		    workbook.write(fileOut);
		    fileOut.close();
		    }catch (FileNotFoundException e3){
		        e3.printStackTrace();
		    } catch (IOException e4) {
		        e4.printStackTrace();
		    }finally{
		    	try {
		    		rs.close();
		    		st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		JOptionPane.showMessageDialog(null, "Details are saved on a file for printing.\nCheck it for the specified folder C:/i-LibraryDetailsAdmin");
	}
	void updatemenu(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();
		int mysqlport=MySQLconfiguration.getport();
		Connection con=null;
		Statement stmnt = null;
		String update_name = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
			stmnt=con.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JTextField pieces = new JTextField();
		Object[] message = {
		    "Enter the correct name of the student you want to change, if there is a mistake in name !", pieces,
		};
 
     int option = JOptionPane.showConfirmDialog(null, message, "Update student", JOptionPane.OK_CANCEL_OPTION);
		  if (option == JOptionPane.OK_OPTION) {
			     if(selectedRow==null){
			    	 JOptionPane.showMessageDialog(null, "Select corresponding row of student to update");
			     }
			     else if(pieces.getText().equals("")||(pieces.getText())==null){
		            JOptionPane.showMessageDialog(null, "Enter the correct name to be changed");
		          } 
		         else{
		           update_name=(pieces.getText());
		           String sql="update student set name='"+update_name+"' where pnr='"+selectedRow+"'";
		 		  try {
		 		    stmnt.executeUpdate(sql);
		 			   } catch (SQLException e) {
		 			    	e.printStackTrace();
		 			  }
		 		     finally{
		 		       try {
						con.close();
						selectedRow=0;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 		     } 
		         }
		
			 /*--------------*/
			     
	   Connection conn=null;
	   String name=studentname.toString();
	   @SuppressWarnings("unused")
	String prn=selectedRow.toString();
	   Statement st1 = null,st2=null;
	    String sql4="create table $tableName (book_id int(10),book_name varchar(40),status varchar(10))";
		String table=(update_name+selectedRow);
		String sqlquery=sql4.replace("$tableName", table);
		String sql5="insert into "+table+" select * from $from";
		//String sql1=sql5.replace("$tableName", table);
		String replacement=(name+selectedRow);
		String sql2=sql5.replace("$from", replacement);
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/oneeachdb",mysqlusername,mysqlpassword);
			 st1=conn.createStatement();
			 st2=conn.createStatement();
			 st1.execute(sqlquery);
			 st2.execute(sql2);
			  } catch (ClassNotFoundException | SQLException e1) {
			    e1.printStackTrace();
			  }
		      finally{
		    	  try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }
		} 
     else{
		  System.out.println("Update canceled");
		 }
   }
	void deletemenu(){
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		Statement stmnt=db.getStatementobj();
		Object[] options = {"Yes, please",
                "No, thanks"};
int option = JOptionPane.showOptionDialog(null,"Would you like to delete the student from database?"+
                "\n Warning: If you want to delete" +
                " multiple students, do not refresh in the middle.","Delete the student",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,
         options[1]);
		  if (option == JOptionPane.OK_OPTION) {
			     if(selectedRow==null){
			    	 JOptionPane.showMessageDialog(null, "Select corresponding row to delete the student");
			     }
		         else{
		           String sql="delete from student where pnr='"+selectedRow+"'";
		 		  try {
		 		    stmnt.executeUpdate(sql);
		 			   } catch (SQLException e) {
		 			    	e.printStackTrace();
		 			  }
		 		     finally{
		 		       DBconnection dclose = new DBconnection();
		 		       dclose.dbClose();
		 		       selectedRow=0;
		 		     } 
		         }
		      } 
		  else{
		      System.out.println("Update canceled");
		   }
	}
	void refreshmenu(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();
		int mysqlport=MySQLconfiguration.getport();
		Connection cn=null;
		Statement stmt = null;
		ResultSet res;
		String query="select pnr,name from student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
			stmt=cn.createStatement();
			res=stmt.executeQuery(query);
			table = new JTable();
			table.setModel(DbUtils.resultSetToTableModel(res));
			scrollPane.setViewportView(table);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
