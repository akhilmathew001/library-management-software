package libraryGUI;

import java.awt.Choice; 
import java.awt.EventQueue;      
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class printBook extends JFrame {
	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;
	JLabel txtf;
	
	int roW;
	String bookNAME[],authNAME[],department[];
	int bookID[],nopiece[];
	int updatekey = 0;
	Object selectedRow;
	Choice updatelist;
	String sql,searchterm;
	Connection conn;
	ResultSet reset1=null,reset2=null;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					printBook frame = new printBook();
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
	public printBook(){
		String sql="select * from shelf";
		Statement stmt1=null;
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		conn=db.getConn();
		try {
			stmt1=conn.createStatement();
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}
		try {
			reset1=stmt1.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		JMenuItem Updatemenu = new JMenuItem("Update book");
		Updatemenu.setIcon(new ImageIcon("C:\\iLibrary images\\Update-icon.png"));
		mnNewMenu.add(Updatemenu);
		Updatemenu.addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updatemenu();
			}
			
		});
		
		JMenuItem Deletemenu = new JMenuItem("Delete book");
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
		table.setModel(DbUtils.resultSetToTableModel(reset1));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            selectedRow=table.getValueAt(table.getSelectedRow(),0);
	        }
	    });
	}
	@SuppressWarnings("deprecation")
	void printDATA(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();;
		int mysqlport=MySQLconfiguration.getport();
		Connection con = null;
		ResultSet rs=null;
		Statement st = null;
        String sql="select * from shelf";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
			st=con.createStatement();
	        rs = st.executeQuery(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		    HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet("Book details");
		    HSSFRow rowhead = sheet.createRow((short) 0);
		    rowhead.createCell((short) 0).setCellValue("BOOK ID");
		    rowhead.createCell((short) 1).setCellValue("BOOK NAME");
		    rowhead.createCell((short) 2).setCellValue("AUTHOR NAME");
		    rowhead.createCell((short) 3).setCellValue("DEPARTMENT");
		    rowhead.createCell((short) 4).setCellValue("NO OF PIECES");
		    int i = 1;
		    try {
				while (rs.next()){
				    HSSFRow row = sheet.createRow((short) i);
				    row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(1)));
				    row.createCell((short) 1).setCellValue(rs.getString(2));
				    row.createCell((short) 2).setCellValue(rs.getString(3));
				    row.createCell((short) 3).setCellValue(rs.getString(4));
				    row.createCell((short) 4).setCellValue(rs.getInt(5));
				    i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    String printdetail = "C:/i-LibraryDetailsAdmin/Bookdetails.xls";
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
		int flag=0;
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		updatelist = new Choice();
		updatelist.add("Update book name");
		updatelist.add("Update author name");
		updatelist.add("Update department");
		updatelist.add("Update no of pieces");
		Statement stmnt=db.getStatementobj();
		JTextField updateTF = new JTextField();
		txtf = new JLabel();
		Object[] message = {
	"Select which option of the book to be updated \nWarning: If you want to update multiple books, do not refresh in the middle." , updatelist, txtf, updateTF};
		updatelist.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(("Update book name").equals(updatelist.getSelectedItem())){
					updatekey=1;
					txtf.setText("Enter the new name of the book");
				}
				else if(("Update author name").equals(updatelist.getSelectedItem())){
					updatekey=2;
					txtf.setText("Enter the new name of the author");
				}
				else if(("Update department").equals(updatelist.getSelectedItem())){
					updatekey=3;
					txtf.setText("Enter the correct name of the department");
				}
				else if(("Update no of pieces").equals(updatelist.getSelectedItem())){
					updatekey=4;
					txtf.setText("Enter how many books you want to update ");
				}
			}
		});
 
     int option = JOptionPane.showConfirmDialog(null, message, "Update book", JOptionPane.OK_CANCEL_OPTION);
		  if (option == JOptionPane.OK_OPTION) {
			     if(selectedRow==null){
			    	 JOptionPane.showMessageDialog(null, "Select corresponding row of the book to update");
			     }
			     else if(updateTF.getText().equals("")||(updateTF.getText())==null){
		            JOptionPane.showMessageDialog(null, "Enter a value to procced");
		          } 
		         else{
		        	 searchterm=updateTF.getText();
		        	 if(updatekey==1){
		        		 sql="update shelf set book_name='"+searchterm+"' where book_id="+selectedRow+"";
		        	 }
		        	 else if(updatekey==2){
		        		 sql="update shelf set author='"+searchterm+"' where book_id="+selectedRow+"";
		        	 }
                     else if(updatekey==3){
                    	 sql="update shelf set department='"+searchterm+"' where book_id="+selectedRow+"";
		        	 }
                     else if(updatekey==4){
                    	 int update_no=Integer.valueOf(searchterm);
                    	 sql="update shelf set pieces=pieces+"+update_no+" where book_id="+selectedRow+"";
		        	 }
                     else{
                    	 JOptionPane.showMessageDialog(null, "Select any option from the list");
                     }
		 		  try {
		 		    stmnt.executeUpdate(sql);
		 			   } catch (SQLException e) {
		 			    	e.printStackTrace();
		 			    	flag=1;
		 			  }
		 		     finally{
		 		       DBconnection dclose = new DBconnection();
		 		       dclose.dbClose();
		 		       selectedRow=0;
		 		       if(flag==0){
		 		       JOptionPane.showMessageDialog(null, "Update finished");
		 		       }
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
int option = JOptionPane.showOptionDialog(null,"Would you like to delete the book from database.?"+ " \nWarning: If you want to delete" +
                " multiple books, do not refresh in the middle.","Delete the book",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,
         options[1]);
		  if (option == JOptionPane.OK_OPTION) {
			     if(selectedRow==null){
			    	 JOptionPane.showMessageDialog(null, "Select corresponding row to delete the book");
			     }
		         else{
		           String sql="delete from shelf where book_id='"+selectedRow+"'";
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
			     option=-1;
		      } 
		  else{
			 option=-1; 
		     System.out.println("Update canceled");
		   }
	}
	void refreshmenu(){
		ResultSet rs=null;
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		Statement stmnt=db.getStatementobj();
		try {
			rs=stmnt.executeQuery("select * from shelf");
			table = new JTable();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			scrollPane.setViewportView(table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
