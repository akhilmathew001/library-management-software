
package libraryGUI;

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.swing.JList;

public class StudentPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel studentPane;
	private JTextField NameField;
	private JTextField prnField;
	JPanel credentialpanel;
	private JLabel enternameLabel;
	private JLabel prnLabel;
	private JButton studentLoginButton;
	JRadioButton rdbtnNAME;
	JRadioButton rdbtnAuthor;
	JRadioButton rdbtnDepartment;
	JScrollPane scrollPane;
	Pattern pattern;
	Matcher matcher;
	String nameT;
	int PRNT=0,flag=0,rowCount;
	int pnrD[]=new int[30];
	public int searchKEY;
	public static String searchName;
	String nameVD[]=new String[30];
	String tabHead[]={"BOOK ID","BOOK NAME","AUTHOR","DEPARTMENT","NO. OF PIECES"};
	Integer book_idD[],no_piecesD[];
	String nameD[],authorD[],deptD[];
	static Object data[][];
	Connection conn;
	static Statement stmt1,stmt2,stmt3;
	private JPanel Stdsearchpanel;
	JPanel studentlogpanel;
	CardLayout cardLayout;
	private JPanel booksrchpanel;
	private JTextField searchkeyField;
	private JPanel resultpanel;
	private JButton srchagainBTN;
	private JButton returnBTN;
	private JTable resultTable;
	private JButton btnPrint;
	private JLabel lblNotification;
	private JLabel imageLBL;
	private JList list;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPage frame = new StudentPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentPage() {
		setTitle("i-Library: STUDENT WINDOW");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\iLibrary images\\Student-3-icon.png"));
		setFont(new Font("Tekton Pro Ext", Font.BOLD, 11));
		setTitle("STUDENT PAGE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 508);
		studentPane = new JPanel();
		studentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(studentPane);
		studentPane.setLayout(new CardLayout(0, 0));
		
		studentlogpanel = new JPanel();
		studentPane.add(studentlogpanel, "STD_LOGIN_CARD");
		
		credentialpanel = new JPanel();
		credentialpanel.setBackground(SystemColor.textHighlight);
		credentialpanel.setForeground(Color.ORANGE);
	    credentialpanel.setBorder(new TitledBorder("Login validation"));
		
		imageLBL = new JLabel("");
		imageLBL.setIcon(new ImageIcon("C:\\iLibrary images\\Student Login page.jpg"));
		
		list = new JList();
		GroupLayout gl_studentlogpanel = new GroupLayout(studentlogpanel);
		gl_studentlogpanel.setHorizontalGroup(
			gl_studentlogpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_studentlogpanel.createSequentialGroup()
					.addGroup(gl_studentlogpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_studentlogpanel.createSequentialGroup()
							.addGap(22)
							.addComponent(imageLBL, GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(credentialpanel, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_studentlogpanel.createSequentialGroup()
							.addGap(78)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_studentlogpanel.setVerticalGroup(
			gl_studentlogpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_studentlogpanel.createSequentialGroup()
					.addGroup(gl_studentlogpanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_studentlogpanel.createSequentialGroup()
							.addGap(61)
							.addComponent(credentialpanel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_studentlogpanel.createSequentialGroup()
							.addGap(43)
							.addComponent(list)
							.addGap(18)
							.addComponent(imageLBL, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(102))
		);
		credentialpanel.setLayout(null);
		
		prnField = new JTextField();
		prnField.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 12));
		prnField.setBounds(257, 159, 220, 36);
		credentialpanel.add(prnField);
		prnField.setColumns(10);
		
		enternameLabel = new JLabel("Enter Your Name");
		enternameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		enternameLabel.setBounds(81, 64, 118, 14);
		credentialpanel.add(enternameLabel);
		
		prnLabel = new JLabel("Enter Your PRN");
		prnLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		prnLabel.setBounds(81, 170, 118, 14);
		credentialpanel.add(prnLabel);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		NameField.setBounds(257, 53, 220, 36);
		credentialpanel.add(NameField);
		NameField.setColumns(10);
		
		studentLoginButton = new JButton("LOGIN\r");
		studentLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					nameT=NameField.getText();
				    PRNT=Integer.valueOf(prnField.getText().toString()); 
				}catch(IllegalArgumentException e){
					pattern = Pattern.compile("^[a-zA-Z]+$");  
				    matcher = pattern.matcher(nameT);
				if(nameT.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Name field is empty");
					flag++;
				}
			    else if(!matcher.find()){  
			     JOptionPane.showMessageDialog(null, "Enter a valid username");
			     flag++;
			    }
				else if(PRNT==0){
					JOptionPane.showMessageDialog(null, "PRN field is empty or not valid");
					flag++;
				}
			}
			if(flag>0){
				NameField.setText(null);
				prnField.setText(null);
				flag=0;
			}
			if(flag==0){
				getDataDB();
			}
				System.out.println(nameT);
				System.out.println(PRNT);
		   }
		});
		studentLoginButton.setFont(new Font("Segoe Script", Font.BOLD, 12));
		studentLoginButton.setBounds(216, 244, 109, 23);
		credentialpanel.add(studentLoginButton);
		studentlogpanel.setLayout(gl_studentlogpanel);
		
		Stdsearchpanel = new JPanel();
		studentPane.add(Stdsearchpanel, "BOOK_SEARCH_CARD");
		
		booksrchpanel = new JPanel();
		booksrchpanel.setBorder(new TitledBorder("Search Methods"));
		booksrchpanel.setBackground(SystemColor.activeCaption);
		GroupLayout gl_Stdsearchpanel = new GroupLayout(Stdsearchpanel);
		gl_Stdsearchpanel.setHorizontalGroup(
			gl_Stdsearchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Stdsearchpanel.createSequentialGroup()
					.addGap(29)
					.addComponent(booksrchpanel, GroupLayout.PREFERRED_SIZE, 772, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_Stdsearchpanel.setVerticalGroup(
			gl_Stdsearchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Stdsearchpanel.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addComponent(booksrchpanel, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		booksrchpanel.setLayout(null);
		
		rdbtnNAME = new JRadioButton("SEARCH BY BOOK NAME");
		rdbtnNAME.setBounds(233, 88, 311, 23);
		booksrchpanel.add(rdbtnNAME);
		rdbtnNAME.setSelected(true);
		rdbtnNAME.setActionCommand("name");
		
		rdbtnAuthor = new JRadioButton("SEARCH BY AUTHOR NAME");
		rdbtnAuthor.setBounds(233, 142, 311, 23);
		booksrchpanel.add(rdbtnAuthor);
		rdbtnAuthor.setActionCommand("author");
		
		rdbtnDepartment = new JRadioButton("SEARCH BY DEPARTMENT");
		rdbtnDepartment.setBounds(233, 204, 311, 23);
		booksrchpanel.add(rdbtnDepartment);
		rdbtnDepartment.setActionCommand("department");
		if(rdbtnNAME.isSelected()){
			searchKEY=1;
		}
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNAME);
		group.add(rdbtnAuthor);
		group.add(rdbtnDepartment);
		
		rdbtnNAME.addActionListener(this);
		rdbtnAuthor.addActionListener(this);
		rdbtnDepartment.addActionListener(this);
		
		JButton entersearch = new JButton("ENTER");
		entersearch.setFont(new Font("Segoe Script", Font.BOLD, 12));
		entersearch.setBounds(325, 338, 100, 23);
		booksrchpanel.add(entersearch);
		entersearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				flag=0;
            	searchName=searchkeyField.getText();
            	pattern = Pattern.compile("^[a-zA-Z]+$");  
            	matcher = pattern.matcher(searchName);
            	if(searchName.trim().isEmpty()){
            		JOptionPane.showMessageDialog(null, "Search term can't be empty");
            		flag++;
            		searchkeyField.setText(null);
            	}
            	else if(!matcher.find()){  
   			     JOptionPane.showMessageDialog(null, "Enter a valid search term");
   			     flag++;
   			     searchkeyField.setText(null);
   			    }
            	if(flag==0){
				displayData();
            	}
            	flag=0;
			}
		});
		
		searchkeyField = new JTextField();
		searchkeyField.setBounds(233, 267, 311, 35);
		booksrchpanel.add(searchkeyField);
		searchkeyField.setColumns(10);
		
		JLabel lblsearchKEY = new JLabel("Enter the search term");
		lblsearchKEY.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblsearchKEY.setBounds(48, 277, 149, 14);
		booksrchpanel.add(lblsearchKEY);
		Stdsearchpanel.setLayout(gl_Stdsearchpanel);
		
		resultpanel = new JPanel();
		studentPane.add(resultpanel, "name_639258187385174");
		resultpanel.setLayout(null);
		
		srchagainBTN = new JButton("SEARCH AGAIN");
		srchagainBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		srchagainBTN.setBounds(48, 384, 148, 40);
		resultpanel.add(srchagainBTN);
		srchagainBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout = (CardLayout) studentPane.getLayout();
				cardLayout.show(studentPane,"BOOK_SEARCH_CARD");
				searchkeyField.setText(null);
				
			}
		});
		
		returnBTN = new JButton("RETURN");
		returnBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		returnBTN.setBounds(650, 384, 148, 40);
		resultpanel.add(returnBTN);
		returnBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.main(null);
				setVisible(false);
				dispose();
			}
		});
		
		btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				PrintResult.main(null);
				String mysqlusername=MySQLconfiguration.getuser();
				String mysqlpassword=MySQLconfiguration.getpass();
				int mysqlport=MySQLconfiguration.getport();
				Connection con = null;
				ResultSet rs=null;
				PreparedStatement st = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/library_books",mysqlusername,mysqlpassword);
					if(searchKEY==1){
				        String sql1="select * from shelf where  book_name like '$name'";
				        String sqlquery=sql1.replace("$name", StudentPage.searchName);
				        st=con.prepareStatement(sqlquery);
						}
						else if(searchKEY==2){
				        String sql2="select * from shelf where author like '$name'";
				        String sqlquery=sql2.replace("$name", StudentPage.searchName);
				        st=con.prepareStatement(sqlquery);
						}else if(searchKEY==3){
						String sql3="select * from shelf where department like '$name'";
						String sqlquery=sql3.replace("$name", StudentPage.searchName);
				        st=con.prepareStatement(sqlquery);
						}
					rs = st.executeQuery();
			       
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    String printdetail = "C:/i-LibraryDetailsstudent/Booksearchdetails_"+nameT+".xls";
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
			}});
		btnPrint.setFont(new Font("Segoe Script", Font.BOLD, 12));
		btnPrint.setBounds(358, 384, 145, 40);
		resultpanel.add(btnPrint);
		
		resultTable = new JTable();
		resultTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resultTable.setModel(new DefaultTableModel());
		resultTable.setBounds(800, 36, -757, 343);
		JScrollPane scrollPaneTB = new JScrollPane(resultTable);
		scrollPaneTB.setViewportBorder(new CompoundBorder());
		scrollPaneTB.setBounds(0, 52, 844, 263);
		resultpanel.add(scrollPaneTB);
		
		lblNotification = new JLabel("New label");
		lblNotification.setBounds(10, 11, 348, 14);
		resultpanel.add(lblNotification);
		cardLayout = (CardLayout) studentPane.getLayout();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="name"){
			searchKEY=1;
			System.out.println(searchKEY);
		}
		else if(e.getActionCommand()=="author"){
			searchKEY=2;
			System.out.println(searchKEY);
		}
		else if(e.getActionCommand()=="department"){
			searchKEY=3;
			System.out.println(searchKEY);
		}
		
	}
	
	void getDataDB(){
		int flag1=0;
		String sql="select pnr,name from student";
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		stmt1=db.getStatementobj();
		ResultSet reset=null;
		try {
			reset=stmt1.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int i=0;
			while(reset.next()){
			  pnrD[i]=reset.getInt(1);
			  nameVD[i]=reset.getString(2);
			  if(pnrD[i]==PRNT&&nameVD[i].equalsIgnoreCase(nameT)){
					flag1=flag1+1;
				}
			  i++;
		 }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		DBconnection dclose = new DBconnection();
		dclose.dbClose();
	}
		if(flag1>0){
		   JOptionPane.showMessageDialog(null, "Authentication validated");
		   cardLayout.next(studentPane);
		}
		else{
			JOptionPane.showMessageDialog(null, "Enter correct details");
		} 
	}
	void displayData(){
		cardLayout.next(studentPane);  
		String sql=null;
		String sqlC="select count(*) from shelf";
		if(searchKEY==1){
		   sql="select * from shelf where book_name like '"+searchName+"'";
		   System.out.println("11");
		}
		else if(searchKEY==2){
			sql="select * from shelf where author like '"+searchName+"'";
			System.out.println("22");
		}
		else if(searchKEY==3){
			sql="select * from shelf where department like '"+searchName+"'";
			System.out.println("33");
		}
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		conn=db.getConn();
		try {
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet reset1=null,rs=null;
		try {
			reset1=stmt2.executeQuery(sql);
			rs=stmt3.executeQuery(sqlC);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				rowCount=rs.getInt(1);
				System.out.println(rowCount);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!reset1.next()){
	             JOptionPane.showMessageDialog(null, "No book found for specified search");
	             lblNotification.setText("0 books found.. Please try again with another keyword");
	             Object[][] data = {{"Found 0 books"},
	   		           {"Not found"},
	   		           {"Search Again"},
	   		           {"Not found"},
	   		           {"Try another keyword",} };
	             resultTable.setModel(new DefaultTableModel(data,tabHead));
				}
			else{
		        book_idD=new Integer[rowCount+1];
		        nameD=new String[rowCount+1];
		        authorD=new String[rowCount+1];
		        deptD=new String[rowCount+1];
		        no_piecesD=new Integer[rowCount+1];
		        int i=0;
			do{
				book_idD[i]=reset1.getInt(1);
				nameD[i]=reset1.getString(2);
				authorD[i]=reset1.getString(3);
				deptD[i]=reset1.getString(4);
				no_piecesD[i]=reset1.getInt(5);
				i++;
			 }while(reset1.next());
			for(int f=0;f<i;f++){
				System.out.println(book_idD[f]+nameD[f]+authorD[f]+deptD[f]+no_piecesD[f]);
			}
			int d=0;
		    data=new Object[rowCount+1][5+1];
			for(int j=0;j<i;j++){
				for(int k=0;k<1;k++){
					for(int m=d;m<i;m++){
				        if(k==0){
					      data[j][k]=book_idD[d];
					      System.out.println(data[j][k]);
				           }
				   }
				}
				d++;
			}
			d=0;
			for(int j=0;j<i;j++){
				for(int k=0;k<2;k++){
					for(int m=d;m<i;m++){
				        if(k==1){
					      data[j][k]=nameD[d];
				           }
				   }
				}
				d++;
			}
			d=0;
			for(int j=0;j<i;j++){
				for(int k=0;k<3;k++){
					for(int m=d;m<i;m++){
				        if(k==2){
					      data[j][k]=authorD[d];
				           }
				   }
				}
				d++;
			}
			d=0;
			for(int j=0;j<i;j++){
				for(int k=0;k<4;k++){
					for(int m=d;m<i;m++){
				        if(k==3){
					      data[j][k]=deptD[d];
				           }
				   }
				}
				d++;
			}
			d=0;
			for(int j=0;j<i;j++){
				for(int k=0;k<5;k++){
					for(int m=d;m<i;m++){
				        if(k==4){
					      data[j][k]=no_piecesD[d];
				           }
				   }
				}
				d++;
			} 
			  lblNotification.setText("Search Results");
              resultTable.setModel(new DefaultTableModel(data,tabHead));
	          resultTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
              resultTable.setFillsViewportHeight(true);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBconnection dClose = new DBconnection();
			  dClose.dbClose();
		}
	}
	static Object[][] getTable(){
		return data;
	}
}
