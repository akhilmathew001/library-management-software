package libraryGUI;

import java.awt.EventQueue;   

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SuppressWarnings("serial")
public class AdminPage extends JFrame implements ActionListener{

	private JPanel admnPane;
	JPanel validationpanel;
	private JLabel lblpassword;
	private JButton btnAdmnLog;
	private JPasswordField admnpassField;
	private JPanel Optionpanel;
	private JPanel Dispanel;
	private JRadioButton rdbtnAddbooks;
	private JRadioButton rdbtnViewBook;
	private JRadioButton rdbtnAllot;
	private JRadioButton rdbtnReturnBook;
	private JRadioButton rdbtnStdDB;
	private JRadioButton rdbtnSearchStd;
	private JRadioButton rdbtnPrint;
	private JRadioButton rdbtnExit;
	private JLabel lblNewLabel;
	private JButton NextBTN;
	public CardLayout cardManager;
	ButtonGroup RDBG;
	int opkEY;
	private JPanel addbookpanel;
	private JPanel panel;
	private JTextField idTF;
	private JTextField nameTF;
	private JTextField authorTF;
	private JTextField depTF;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton bookaddBtn;
	private JLabel lblAddDetailsOf;
	private JPanel stdManagePanel;
	private JPanel printPanel;
	private JPanel returnPanel;
	private JPanel allotPanel;
	private JPanel viewstddetailPanel;
	private JPanel viewallbookPanel;
	private JPanel updatebookPanel;
	private JPanel updatePanel;
	private JButton updateBTN;
	private JTextField updateIDTF;
	private JTextField updateNOTF;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton updatepanelBTN;
	private JPanel stdDBPanel;
	private JTextField stdnameTF;
	private JTextField prnTF;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JButton addstdBTN;
	private JPanel panel_1;
	private JLabel lblNewLabel_9;
	private JButton viewAllBTN;
	private JPanel searchSTD;
	private JButton searchstdBTN;
	private JPanel panel_2;
	private JTextField allotPRNTF;
	private JTextField allotIDTF;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JButton allotBTN;
	private JPanel panel_3;
	private JTextField returnPRNTF;
	private JTextField returnIDTF;
	private JLabel label;
	private JLabel label_1;
	private JButton btnReturn;
	private JPanel panel_4;
	private JTextField printPRNTF;
	private JLabel label_2;
	private JButton admnprintBTN;
	
	int bookID,no_ofPieces,PRN,flag;
	String book_name,author_name,dept,StudentName;
	public static String nameAdmin;
	public String adminpassword;
	public String preID;
	String passWord;
	Pattern pattern;
	Matcher matcher;
	private JLabel lblEnterTheNo;
	private JTextField pieceTF;
	Connection conn;
	static Object data[][];
	static int book_idD[];
	static int no_piecesD[];
	static String nameD[];
	static String authorD[];
	static String deptD[];
	static int rowCount=0;
	public static String status1="in use",status2="returned";
	private JLabel lblEnterBookName;
	private JTextField allotSTDNTF;
	private JTextField rtnstudentname;
	private JTextField printnameTF;
	private JLabel lblEnterStudentName_1;
	public static ResultSet rsforprint;
	private JLabel imageL;
	private JTextField nameadmnTF;
	private JTextField admnpassTF;
	private JTextField adminID;
	private JButton Qbtn;
	
	String mysqluser,mysqlpass;
	int mysqlport;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AdminPage(){
		mysqluser=MySQLconfiguration.getuser();
		mysqlpass=MySQLconfiguration.getpass();
		mysqlport=MySQLconfiguration.getport();
		setTitle("i-Library: ADMIN PAGE");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\iLibrary images\\admin.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 508);
		admnPane = new JPanel();
		admnPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(admnPane);
		admnPane.setLayout(new CardLayout(0, 0));
		
		JPanel logpanel = new JPanel();
		admnPane.add(logpanel, "adminlogin");
		
		validationpanel = new JPanel();
		validationpanel.setBackground(new Color(112, 128, 144));
		validationpanel.setBorder(new TitledBorder("Admin login"));
		
		imageL = new JLabel("");
		imageL.setIcon(new ImageIcon("C:\\iLibrary images\\secured-login3.png"));
		GroupLayout gl_logpanel = new GroupLayout(logpanel);
		gl_logpanel.setHorizontalGroup(
			gl_logpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_logpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(validationpanel, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(imageL, GroupLayout.PREFERRED_SIZE, 296, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_logpanel.setVerticalGroup(
			gl_logpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_logpanel.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addGroup(gl_logpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(imageL, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
						.addComponent(validationpanel, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
					.addGap(74))
		);
		validationpanel.setLayout(null);
		
		lblpassword = new JLabel("Enter administrartor password:");
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblpassword.setBounds(31, 65, 203, 35);
		validationpanel.add(lblpassword);
		
		admnpassField = new JPasswordField();
		admnpassField.setBounds(254, 66, 223, 35);
		validationpanel.add(admnpassField);
		logpanel.setLayout(gl_logpanel);
		
		btnAdmnLog = new JButton("LOGIN");
		btnAdmnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				passWord= new String(admnpassField.getPassword());
				System.out.println(passWord);
				if(passWord.equalsIgnoreCase("admin123")){
					cardManager=(CardLayout) admnPane.getLayout();
				    cardManager.show(admnPane, "OP PANEL");
				}
				else{
					validateadmindb();
				}
			}
		});
		btnAdmnLog.setFont(new Font("Segoe Script", Font.BOLD, 12));
		btnAdmnLog.setBounds(99, 180, 133, 30);
		validationpanel.add(btnAdmnLog);
		
		JButton newadmnbtn = new JButton("NEW ADMIN?");
		newadmnbtn.setFont(new Font("Segoe Script", Font.BOLD, 12));
		newadmnbtn.setBounds(316, 180, 133, 30);
		validationpanel.add(newadmnbtn);
		newadmnbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager=(CardLayout) admnPane.getLayout();
				cardManager.show(admnPane,"adminregpanel");
			}
		});
		
		
		JPanel admnregpanel = new JPanel();
		admnPane.add(admnregpanel, "adminregpanel");
		
		JPanel admnreg = new JPanel();
		admnreg.setBackground(Color.GRAY);
		GroupLayout gl_admnregpanel = new GroupLayout(admnregpanel);
		gl_admnregpanel.setHorizontalGroup(
			gl_admnregpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_admnregpanel.createSequentialGroup()
					.addGap(186)
					.addComponent(admnreg, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		gl_admnregpanel.setVerticalGroup(
			gl_admnregpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_admnregpanel.createSequentialGroup()
					.addGap(114)
					.addComponent(admnreg, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		admnreg.setLayout(null);
		admnreg.setBorder(new TitledBorder(null, "Register as a new admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		nameadmnTF = new JTextField();
		nameadmnTF.setBounds(263, 19, 207, 35);
		admnreg.add(nameadmnTF);
		nameadmnTF.setColumns(10);
		
		admnpassTF = new JTextField();
		admnpassTF.setBounds(263, 84, 207, 35);
		admnreg.add(admnpassTF);
		admnpassTF.setColumns(10);
		
		adminID = new JTextField();
		adminID.setBounds(263, 150, 207, 35);
		admnreg.add(adminID);
		adminID.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("ENTER YOUR NAME:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13.setBounds(37, 28, 142, 14);
		admnreg.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("ENTER YOUR PASSWORD:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_14.setBounds(37, 93, 171, 14);
		admnreg.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("ENTER ADMIN ID:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.setBounds(37, 159, 151, 14);
		admnreg.add(lblNewLabel_15);
		
		JButton regbtn = new JButton("REGISTER");
		regbtn.setFont(new Font("Segoe Script", Font.BOLD, 12));
		regbtn.setBounds(37, 231, 118, 30);
		admnreg.add(regbtn);
		regbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					nameAdmin=nameadmnTF.getText();
				    adminpassword=admnpassTF.getText();
				    preID=adminID.getText();
				}catch(IllegalArgumentException s){
					pattern = Pattern.compile("^[a-zA-Z]+$");  
				    matcher = pattern.matcher(nameAdmin);
				if(nameAdmin.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Name field is empty");
					flag++;
				}
			    else if(!matcher.find()){  
			     JOptionPane.showMessageDialog(null, "Enter a valid username");
			     flag++;
			    }
				else if(adminpassword==null){
					JOptionPane.showMessageDialog(null, "Password field is empty or not valid");
					flag++;
				}
			}
			if(flag>0){
				nameadmnTF.setText(null);
				admnpassTF.setText(null);
				adminID.setText(null);
				flag=0;
			}
			if(flag==0){
				if(preID.equalsIgnoreCase("admin123")){
				   connectAdmnDB();
				 }
				else{
					JOptionPane.showMessageDialog(null, "Entered Admin ID is wrong. Enter correct ID to create an administrator account");
					adminID.setText(null);
				}
			}
				System.out.println(adminpassword);
				System.out.println(adminpassword);
			}
		});
		
		Qbtn = new JButton("What is an admin id?");
		Qbtn.setFont(new Font("Tahoma", Font.ITALIC, 13));
		Qbtn.setBounds(278, 235, 161, 23);
		admnreg.add(Qbtn);
		admnregpanel.setLayout(gl_admnregpanel);
		Qbtn.setBorderPainted(false);
		final Object msg[]={"An admin ID is a pre defined password used to create an administrator account, which comes along with the application"};
		Qbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, msg,"Important info !",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		Optionpanel = new JPanel();
		admnPane.add(Optionpanel, "OP PANEL");
		
		Dispanel = new JPanel();
		Dispanel.setBackground(new Color(169, 169, 169));
		Dispanel.setForeground(new Color(169, 169, 169));
		Dispanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Admin Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_Optionpanel = new GroupLayout(Optionpanel);
		gl_Optionpanel.setHorizontalGroup(
			gl_Optionpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Optionpanel.createSequentialGroup()
					.addGap(19)
					.addComponent(Dispanel, GroupLayout.PREFERRED_SIZE, 798, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_Optionpanel.setVerticalGroup(
			gl_Optionpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Optionpanel.createSequentialGroup()
					.addGap(26)
					.addComponent(Dispanel, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		Dispanel.setLayout(null);
		
		rdbtnAddbooks = new JRadioButton("ADD BOOKS");
		rdbtnAddbooks.setBounds(77, 78, 207, 23);
		rdbtnAddbooks.setActionCommand("ADD");
		rdbtnAddbooks.setSelected(true);
		Dispanel.add(rdbtnAddbooks);
		if(rdbtnAddbooks.isSelected()){
			opkEY=1;
		}
		
		rdbtnViewBook = new JRadioButton("MANAGE BOOK DATABASE");
		rdbtnViewBook.setBounds(513, 78, 207, 23);
		rdbtnViewBook.setActionCommand("VIEW");
		Dispanel.add(rdbtnViewBook);
		
		rdbtnAllot = new JRadioButton("ALLOT BOOKS");
		rdbtnAllot.setBounds(77, 148, 207, 23);
		rdbtnAllot.setActionCommand("ALLOT");
		Dispanel.add(rdbtnAllot);
		
		rdbtnReturnBook = new JRadioButton("RETURN BOOKS");
		rdbtnReturnBook.setBounds(513, 148, 207, 23);
		rdbtnReturnBook.setActionCommand("RETURN");
		Dispanel.add(rdbtnReturnBook);
		
		rdbtnStdDB = new JRadioButton("ADD STUDENT");
		rdbtnStdDB.setBounds(77, 212, 207, 23);
		rdbtnStdDB.setActionCommand("DB");
		Dispanel.add(rdbtnStdDB);
		
		rdbtnSearchStd = new JRadioButton("MANAGE STUDENT DATABASE");
		rdbtnSearchStd.setBounds(513, 212, 207, 23);
		rdbtnSearchStd.setActionCommand("SEARCHSTD");
		Dispanel.add(rdbtnSearchStd);
		
		rdbtnPrint = new JRadioButton("PRINT STUDENT DETAILS");
		rdbtnPrint.setBounds(77, 278, 207, 23);
		rdbtnPrint.setActionCommand("PRINT");
		Dispanel.add(rdbtnPrint);
		
		rdbtnExit = new JRadioButton("EXIT");
		rdbtnExit.setBounds(513, 278, 207, 23);
		rdbtnExit.setActionCommand("EXIT");
		Dispanel.add(rdbtnExit);
		
		RDBG = new ButtonGroup();
		RDBG.add(rdbtnAddbooks);
		RDBG.add(rdbtnViewBook);
		RDBG.add(rdbtnAllot);
		RDBG.add(rdbtnReturnBook);
		RDBG.add(rdbtnStdDB);
		RDBG.add(rdbtnSearchStd);
		RDBG.add(rdbtnPrint);
		RDBG.add(rdbtnExit);
		
		rdbtnAddbooks.addActionListener(this);
		rdbtnViewBook.addActionListener(this);
		rdbtnAllot.addActionListener(this);
		rdbtnReturnBook.addActionListener(this);
		rdbtnStdDB.addActionListener(this);
		rdbtnSearchStd.addActionListener(this);
		rdbtnPrint.addActionListener(this);
		rdbtnExit.addActionListener(this);
		
		lblNewLabel = new JLabel("SELECT ANY ONE OPTION");
		lblNewLabel.setForeground(new Color(176, 224, 230));
		lblNewLabel.setLabelFor(Dispanel);
		lblNewLabel.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 14));
		lblNewLabel.setBounds(77, 30, 207, 14);
		Dispanel.add(lblNewLabel);
		
		NextBTN = new JButton("NEXT");
		NextBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		NextBTN.setBounds(333, 336, 135, 35);
		Dispanel.add(NextBTN);
		Optionpanel.setLayout(gl_Optionpanel);
		
		addbookpanel = new JPanel();
		admnPane.add(addbookpanel, "ADD BOOK");
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add books to Library", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(102, 153, 153));
		GroupLayout gl_addbookpanel = new GroupLayout(addbookpanel);
		gl_addbookpanel.setHorizontalGroup(
			gl_addbookpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addbookpanel.createSequentialGroup()
					.addGap(117)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		gl_addbookpanel.setVerticalGroup(
			gl_addbookpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_addbookpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		idTF = new JTextField();
		idTF.setBounds(294, 98, 228, 30);
		idTF.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel.add(idTF);
		idTF.setColumns(10);
		
		nameTF = new JTextField();
		nameTF.setBounds(296, 152, 226, 30);
		nameTF.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel.add(nameTF);
		nameTF.setColumns(10);
		
		authorTF = new JTextField();
		authorTF.setBounds(296, 212, 226, 30);
		authorTF.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel.add(authorTF);
		authorTF.setColumns(10);
		
		depTF = new JTextField();
		depTF.setBounds(296, 266, 226, 30);
		depTF.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel.add(depTF);
		depTF.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Enter the book ID:");
		lblNewLabel_1.setBounds(72, 97, 166, 30);
		lblNewLabel_1.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Enter the book name:");
		lblNewLabel_2.setBounds(72, 151, 188, 30);
		lblNewLabel_2.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Enter the author name:");
		lblNewLabel_3.setBounds(72, 211, 188, 30);
		lblNewLabel_3.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Enter the department:");
		lblNewLabel_4.setBounds(72, 265, 188, 30);
		lblNewLabel_4.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		panel.add(lblNewLabel_4);
		
		bookaddBtn = new JButton("ADD");
		bookaddBtn.setBounds(72, 392, 166, 30);
		bookaddBtn.setFont(new Font("Segoe Script", Font.BOLD, 12));
		bookaddBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try{
					bookID=Integer.valueOf(idTF.getText().toString());
				    book_name=nameTF.getText();
				    author_name=authorTF.getText();
				    dept=depTF.getText();
				    no_ofPieces=Integer.valueOf(pieceTF.getText().toString());
				}catch(IllegalArgumentException e){
					//pattern = Pattern.compile("^[a-zA-Z]+$");  
				    //matcher1 = pattern.matcher(book_name);
			     if(bookID==0||no_ofPieces==0){
						JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
						flag++;
					}
			     else if(book_name.trim().isEmpty()||author_name.trim().isEmpty()||dept.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Fields can't be empty");
					flag++;
			    }
				else if(!(e.equals(null))){
					JOptionPane.showMessageDialog(nameTF, "illegal entry");
				}
			}
			if(flag>0){
				idTF.setText(null);
				nameTF.setText(null);
				authorTF.setText(null);
				depTF.setText(null);
				pieceTF.setText(null);
				flag=0;
			}
			else if(flag==0){
				addBooks(bookID,book_name,author_name,dept,no_ofPieces);
			}
				System.out.println(bookID);
				System.out.println(book_name);
		 }	
		});
		
		panel.add(bookaddBtn);
		
		lblAddDetailsOf = new JLabel("ADD DETAILS OF THE BOOKS");
		lblAddDetailsOf.setBounds(20, 72, 202, 14);
		lblAddDetailsOf.setForeground(new Color(135, 206, 235));
		lblAddDetailsOf.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		panel.add(lblAddDetailsOf);
		
		updateBTN = new JButton("UPDATE");
		updateBTN.setBounds(356, 392, 166, 30);
		updateBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		panel.add(updateBTN);
		
		lblEnterTheNo = new JLabel("Enter the no of pieces:");
		lblEnterTheNo.setBounds(72, 322, 202, 30);
		lblEnterTheNo.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		panel.add(lblEnterTheNo);
		
		pieceTF = new JTextField();
		pieceTF.setBounds(296, 323, 226, 30);
		pieceTF.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		pieceTF.setColumns(10);
		panel.add(pieceTF);
		
		addbookpanel.setLayout(gl_addbookpanel);
		
		updatebookPanel = new JPanel();
		admnPane.add(updatebookPanel, "UPDATE BOOK");

		updateBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent f){
				cardManager=(CardLayout) admnPane.getLayout();
				cardManager.show(admnPane,"UPDATE BOOK");
			}
		});
		
		updatePanel = new JPanel();
		updatePanel.setBackground(new Color(169, 169, 169));
		GroupLayout gl_updatebookPanel = new GroupLayout(updatebookPanel);
		gl_updatebookPanel.setHorizontalGroup(
			gl_updatebookPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_updatebookPanel.createSequentialGroup()
					.addGap(186)
					.addComponent(updatePanel, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		gl_updatebookPanel.setVerticalGroup(
			gl_updatebookPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_updatebookPanel.createSequentialGroup()
					.addGap(117)
					.addComponent(updatePanel, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		updatePanel.setLayout(null);
		
		updateIDTF = new JTextField();
		updateIDTF.setBounds(266, 37, 184, 35);
		updatePanel.add(updateIDTF);
		updateIDTF.setColumns(10);
		
		updateNOTF = new JTextField();
		updateNOTF.setBounds(266, 79, 184, 35);
		updatePanel.add(updateNOTF);
		updateNOTF.setColumns(10);
		
		lblNewLabel_5 = new JLabel("ENTER THE BOOK ID:");
		lblNewLabel_5.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		lblNewLabel_5.setBounds(20, 47, 212, 14);
		updatePanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("ENTER THE NUMBER OF COUNT:");
		lblNewLabel_6.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		lblNewLabel_6.setBounds(20, 89, 236, 14);
		updatePanel.add(lblNewLabel_6);
		
		updatepanelBTN = new JButton("UPDATE BOOK");
		updatepanelBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		updatepanelBTN.setBounds(169, 156, 140, 30);
		updatePanel.add(updatepanelBTN);
		updatebookPanel.setLayout(gl_updatebookPanel);
		
		stdManagePanel = new JPanel();
		admnPane.add(stdManagePanel, "STUDENT DATA BASE");
		updatepanelBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					bookID=Integer.valueOf(updateIDTF.getText().toString());
				    no_ofPieces=Integer.valueOf(updateNOTF.getText().toString());
				}catch(IllegalArgumentException e){
					//pattern = Pattern.compile("^[a-zA-Z]+$");  
				    //matcher1 = pattern.matcher(nameT);
				if(bookID==0||no_ofPieces==0){
					JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
					flag++;
				}
				else if(!(e.equals(null))){
					JOptionPane.showMessageDialog(nameTF, "illegal entry");
				}
			}
			if(flag>0){
				updateIDTF.setText(null);
				updateNOTF.setText(null);
				flag=0;
			}
			else if(flag==0){
				updateBooks(bookID, no_ofPieces);
			}
				System.out.println(bookID);
		 }
		});
		
		stdDBPanel = new JPanel();
		stdDBPanel.setBorder(new TitledBorder(null, "Add students to database", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		stdDBPanel.setBackground(new Color(210, 180, 140));
		GroupLayout gl_stdManagePanel = new GroupLayout(stdManagePanel);
		gl_stdManagePanel.setHorizontalGroup(
			gl_stdManagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stdManagePanel.createSequentialGroup()
					.addGap(150)
					.addComponent(stdDBPanel, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		gl_stdManagePanel.setVerticalGroup(
			gl_stdManagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_stdManagePanel.createSequentialGroup()
					.addContainerGap(49, Short.MAX_VALUE)
					.addComponent(stdDBPanel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
		);
		stdDBPanel.setLayout(null);
		
		stdnameTF = new JTextField();
		stdnameTF.setBounds(254, 77, 198, 30);
		stdDBPanel.add(stdnameTF);
		stdnameTF.setColumns(10);
		
		prnTF = new JTextField();
		prnTF.setBounds(254, 147, 198, 30);
		stdDBPanel.add(prnTF);
		prnTF.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Student name:");
		lblNewLabel_7.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblNewLabel_7.setBounds(84, 86, 138, 14);
		stdDBPanel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Student PRN:");
		lblNewLabel_8.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblNewLabel_8.setBounds(84, 155, 110, 14);
		stdDBPanel.add(lblNewLabel_8);
		
		addstdBTN = new JButton("ADD STUDENT");
		addstdBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		addstdBTN.setBounds(183, 272, 130, 35);
		addstdBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PRN=Integer.valueOf(prnTF.getText().toString());
				    StudentName=stdnameTF.getText();
				}catch(IllegalArgumentException f){
				if(StudentName.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Fields can't be empty");
					flag++;
			    }
				else if(bookID==0||no_ofPieces==0){
					JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
					flag++;
				}
				else if(!(e.equals(null))){
					flag++;
					JOptionPane.showMessageDialog(nameTF, "illegal entry");
				}
			}
			if(flag>0){
				prnTF.setText(null);
				stdnameTF.setText(null);
				flag=0;
			}
			else if(flag==0){
				addStudent(PRN, StudentName);
			}
				System.out.println(bookID);
				System.out.println(book_name);
		 }	
		});
		stdDBPanel.add(addstdBTN);
		stdManagePanel.setLayout(gl_stdManagePanel);
		
		viewallbookPanel = new JPanel();
		admnPane.add(viewallbookPanel, "VIEW ALL");
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBorder(new TitledBorder(null, "View all books", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_viewallbookPanel = new GroupLayout(viewallbookPanel);
		gl_viewallbookPanel.setHorizontalGroup(
			gl_viewallbookPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewallbookPanel.createSequentialGroup()
					.addGap(195)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(185, Short.MAX_VALUE))
		);
		gl_viewallbookPanel.setVerticalGroup(
			gl_viewallbookPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewallbookPanel.createSequentialGroup()
					.addGap(187)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		panel_1.setLayout(null);
		
		lblNewLabel_9 = new JLabel("CLICK TO VIEW ALL BOOKS");
		lblNewLabel_9.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblNewLabel_9.setBounds(10, 30, 174, 14);
		panel_1.add(lblNewLabel_9);
		
		viewAllBTN = new JButton("VIEW");
		viewAllBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		viewAllBTN.setBounds(275, 23, 135, 30);
		viewAllBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewallBooks();
				cardManager.show(admnPane,"OP PANEL");
			}
		});
		panel_1.add(viewAllBTN);
		viewallbookPanel.setLayout(gl_viewallbookPanel);
		
		viewstddetailPanel = new JPanel();
		admnPane.add(viewstddetailPanel, "SEARCH STUDENT");
		
		searchSTD = new JPanel();
		searchSTD.setBorder(new TitledBorder(null, "Search student details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchSTD.setBackground(new Color(245, 245, 220));
		GroupLayout gl_viewstddetailPanel = new GroupLayout(viewstddetailPanel);
		gl_viewstddetailPanel.setHorizontalGroup(
			gl_viewstddetailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_viewstddetailPanel.createSequentialGroup()
					.addContainerGap(149, Short.MAX_VALUE)
					.addComponent(searchSTD, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		gl_viewstddetailPanel.setVerticalGroup(
			gl_viewstddetailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewstddetailPanel.createSequentialGroup()
					.addGap(169)
					.addComponent(searchSTD, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		searchSTD.setLayout(null);
		
		searchstdBTN = new JButton("VIEW STUDENTS");
		searchstdBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		searchstdBTN.setBounds(349, 48, 146, 30);
		searchstdBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintStudentDB.main(null);
				cardManager.show(admnPane, "OP PANEL");
		 }	
		});
		searchSTD.add(searchstdBTN);
		
		JLabel lblNewLabel_10 = new JLabel("CLICK TO VIEW STUDENT DATABASE");
		lblNewLabel_10.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblNewLabel_10.setBounds(28, 48, 240, 38);
		searchSTD.add(lblNewLabel_10);
		viewstddetailPanel.setLayout(gl_viewstddetailPanel);
		
		allotPanel = new JPanel();
		admnPane.add(allotPanel, "ALLOT BOOK");
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 240, 245));
		panel_2.setForeground(SystemColor.controlHighlight);
		panel_2.setBorder(new TitledBorder(null, "Allot books to students", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_allotPanel = new GroupLayout(allotPanel);
		gl_allotPanel.setHorizontalGroup(
			gl_allotPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allotPanel.createSequentialGroup()
					.addGap(133)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_allotPanel.setVerticalGroup(
			gl_allotPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allotPanel.createSequentialGroup()
					.addGap(61)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		panel_2.setLayout(null);
		
		allotPRNTF = new JTextField();
		allotPRNTF.setBounds(299, 61, 196, 30);
		panel_2.add(allotPRNTF);
		allotPRNTF.setColumns(10);
		
		allotIDTF = new JTextField();
		allotIDTF.setBounds(299, 172, 196, 30);
		panel_2.add(allotIDTF);
		allotIDTF.setColumns(10);
		
		allotSTDNTF = new JTextField();
		allotSTDNTF.setColumns(10);
		allotSTDNTF.setBounds(299, 119, 196, 30);
		panel_2.add(allotSTDNTF);
		
		lblNewLabel_11 = new JLabel("Enter student PRN:");
		lblNewLabel_11.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblNewLabel_11.setBounds(84, 70, 148, 14);
		panel_2.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Enter book ID:");
		lblNewLabel_12.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblNewLabel_12.setBounds(84, 181, 120, 14);
		panel_2.add(lblNewLabel_12);
		
		allotBTN = new JButton("ISSUE");
		allotBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		allotBTN.setBounds(205, 264, 135, 30);
		allotBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					bookID=Integer.valueOf(allotIDTF.getText().toString());
				    PRN=Integer.valueOf(allotPRNTF.getText().toString());
				    book_name=allotSTDNTF.getText();
				}catch(IllegalArgumentException k){
				if(bookID==0||PRN==0){
					JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
					flag++;
				}
				else if(book_name.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "student name is empty or not valid");
					flag++;
				}
				else if(!(e.equals(null))){
					flag++;
					JOptionPane.showMessageDialog(null, "illegal entry");
				}
			}
			if(flag>0){
				allotIDTF.setText(null);
				allotPRNTF.setText(null);
				allotSTDNTF.setText(null);
				flag=0;
			}
			else if(flag==0){
				allotBOOK(bookID,PRN,book_name);
			}
				System.out.println(bookID);
				System.out.println(PRN);
		
			}
		});
		panel_2.add(allotBTN);
		
		lblEnterBookName = new JLabel("Enter student name:");
		lblEnterBookName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblEnterBookName.setBounds(84, 123, 160, 14);
		panel_2.add(lblEnterBookName);
		
		allotPanel.setLayout(gl_allotPanel);
		
		returnPanel = new JPanel();
		admnPane.add(returnPanel, "RETURN BOOK");
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Return books from students", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(new Color(245, 245, 220));
		GroupLayout gl_returnPanel = new GroupLayout(returnPanel);
		gl_returnPanel.setHorizontalGroup(
			gl_returnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_returnPanel.createSequentialGroup()
					.addContainerGap(125, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE)
					.addGap(140))
		);
		gl_returnPanel.setVerticalGroup(
			gl_returnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_returnPanel.createSequentialGroup()
					.addGap(63)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		panel_3.setLayout(null);
		
		returnPRNTF = new JTextField();
		returnPRNTF.setBounds(326, 76, 190, 30);
		returnPRNTF.setColumns(10);
		panel_3.add(returnPRNTF);
		
		returnIDTF = new JTextField();
		returnIDTF.setBounds(326, 194, 190, 30);
		returnIDTF.setColumns(10);
		panel_3.add(returnIDTF);
		
		label = new JLabel("Enter student PRN:");
		label.setBounds(74, 84, 140, 16);
		label.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel_3.add(label);
		
		label_1 = new JLabel("Enter book ID:");
		label_1.setBounds(74, 202, 106, 16);
		label_1.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		panel_3.add(label_1);
		
		rtnstudentname = new JTextField();
		rtnstudentname.setColumns(10);
		rtnstudentname.setBounds(326, 134, 190, 30);
		panel_3.add(rtnstudentname);
		
		btnReturn = new JButton("RETURN");
		btnReturn.setBounds(220, 273, 140, 30);
		btnReturn.setFont(new Font("Segoe Script", Font.BOLD, 12));
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					bookID=Integer.valueOf(returnIDTF.getText().toString());
				    PRN=Integer.valueOf(returnPRNTF.getText().toString());
				    StudentName=rtnstudentname.getText();
				}catch(IllegalArgumentException e){
				if(bookID==0||PRN==0||StudentName.equals(null)){
					JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
					flag++;
				}
				else if(!(e.equals(null))){
					flag++;
					JOptionPane.showMessageDialog(nameTF, "illegal entry");
				}
			}
			if(flag>0){
				returnIDTF.setText(null);
				returnPRNTF.setText(null);
				rtnstudentname.setText(null);
				flag=0;
			}
			else if(flag==0){
				returnBook(bookID,PRN,StudentName);
			}
				System.out.println(bookID);
		 }
		});
		panel_3.add(btnReturn);
		
		
		JLabel lblEnterStudentName = new JLabel("Enter student name:");
		lblEnterStudentName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblEnterStudentName.setBounds(74, 142, 164, 16);
		panel_3.add(lblEnterStudentName);
		returnPanel.setLayout(gl_returnPanel);
		
		printPanel = new JPanel();
		admnPane.add(printPanel, "PRINT STUDENT");
		
		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Print student details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(211, 211, 211));
		GroupLayout gl_printPanel = new GroupLayout(printPanel);
		gl_printPanel.setHorizontalGroup(
			gl_printPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_printPanel.createSequentialGroup()
					.addGap(153)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_printPanel.setVerticalGroup(
			gl_printPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_printPanel.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addGap(116))
		);
		panel_4.setLayout(null);
		
		printPRNTF = new JTextField();
		printPRNTF.setColumns(10);
		printPRNTF.setBounds(282, 52, 190, 30);
		panel_4.add(printPRNTF);
		
		label_2 = new JLabel("Enter student PRN:");
		label_2.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		label_2.setBounds(62, 58, 176, 16);
		panel_4.add(label_2);
		
		printnameTF = new JTextField();
		printnameTF.setColumns(10);
		printnameTF.setBounds(282, 105, 190, 30);
		panel_4.add(printnameTF);
		
		admnprintBTN = new JButton("PRINT");
		admnprintBTN.setFont(new Font("Segoe Script", Font.BOLD, 12));
		admnprintBTN.setBounds(178, 180, 140, 30);
		admnprintBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PRN=Integer.valueOf(printPRNTF.getText().toString());
					StudentName=printnameTF.getText();
				}catch(IllegalArgumentException f){
				if(PRN==0||StudentName.equals(null)){
					JOptionPane.showMessageDialog(null, "Fields are empty or not valid");
					flag++;
				}
				else if(!(e.equals(null))){
					flag++;
					JOptionPane.showMessageDialog(null, "illegal entry");
				}
			}
			if(flag>0){
				printPRNTF.setText(null);
				printnameTF.setText(null);
				flag=0;
			}
			else if(flag==0){
				printStudentDetails(PRN,StudentName);
			}
				System.out.println(PRN);
		 }	
		});
		panel_4.add(admnprintBTN);
		
		lblEnterStudentName_1 = new JLabel("Enter student name:");
		lblEnterStudentName_1.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblEnterStudentName_1.setBounds(62, 113, 176, 16);
		panel_4.add(lblEnterStudentName_1);
		printPanel.setLayout(gl_printPanel);
  }

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("ADD")){
			opkEY=1;
			System.out.println("1");
		}
		else if(e.getActionCommand().equals("VIEW")){
			opkEY=2;
			System.out.println("2");
		}
		else if(e.getActionCommand().equals("ALLOT")){
			opkEY=3;
			System.out.println("3");
		}
		else if(e.getActionCommand().equals("RETURN")){
			opkEY=4;
			System.out.println("4");
		}
		else if(e.getActionCommand().equals("DB")){
			opkEY=5;
			System.out.println("5");
		}
		else if(e.getActionCommand().equals("SEARCHSTD")){
			opkEY=6;
			System.out.println("6");
		}
		else if(e.getActionCommand().equals("PRINT")){
			opkEY=7;
			System.out.println("7");
		}
		else if(e.getActionCommand().equals("EXIT")){
			opkEY=100;
			System.out.println("8");
		}
		
	
	NextBTN.addActionListener(new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(opkEY==1){
				cardManager.next(admnPane);
			}
			else if(opkEY==2){
				cardManager.show(admnPane, "VIEW ALL");
			}
			else if(opkEY==3){
				cardManager.show(admnPane, "ALLOT BOOK");

			}
            else if(opkEY==4){
            	cardManager.show(admnPane, "RETURN BOOK");
            	
			}
            else if(opkEY==5){
            	cardManager.show(admnPane, "STUDENT DATA BASE");
            
			}
            else if(opkEY==6){
            	cardManager.show(admnPane, "SEARCH STUDENT");
           
			}
            else if(opkEY==7){
            	cardManager.show(admnPane, "PRINT STUDENT");
            }
            else if(opkEY==100){
            	Object message[]={"Do you wish to exit?"};
            	Object button[]={"Yes,shutdown"};
 int option=JOptionPane.showOptionDialog(null,message,"Shutdown",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE,null,button,button[0]);
            	if(option==JOptionPane.OK_OPTION){
            		JOptionPane.showMessageDialog(null, "i-Library is shutting down......");
            		dispose();
            		System.exit(1000);
            	}
            }
            else{
            	JOptionPane.showMessageDialog(null, "Select any option");
            }
		}
	});	
}
 void addBooks(int book_id,String book_name,String author,String department,int pieces){
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		Statement stmnt=db.getStatementobj();
String sql="insert into shelf (book_id,book_name,author,department,pieces) values ('"+book_id+"','"+book_name+"','"+author+"','"+department+"','"+pieces+"')";
			try {
				stmnt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   finally{
		   System.out.println("Updated the library");
		   DBconnection dclose = new DBconnection();
		   dclose.dbClose();
		   Object[] options = {"Yes ",
           "No "};
    int n = JOptionPane.showOptionDialog(panel,"SUCCESSFULLY ADDED THE BOOK \n WOULD YOU LIKE TO ADD ANOTHER BOOK","STATUS",
           JOptionPane.YES_NO_CANCEL_OPTION,
           JOptionPane.QUESTION_MESSAGE,
           null,
           options,
           options[1]);
         if(n==JOptionPane.YES_NO_OPTION){
        	 cardManager.show(admnPane, "ADD BOOK");
        	    idTF.setText(null);
				nameTF.setText(null);
				authorTF.setText(null);
				depTF.setText(null);
				pieceTF.setText(null);
         }
         else{
        	 cardManager.show(admnPane, "OP PANEL");
         }
		}
	}
  void updateBooks(int ID,int no){
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		Statement stmnt=db.getStatementobj();
        String sql="update shelf set pieces=pieces+'"+no+"' where book_id='"+ID+"'";
			try {
				stmnt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   finally{
		   System.out.println("Updated the library");
		   DBconnection dclose = new DBconnection();
		   dclose.dbClose();
		   Object[] options = {"Yes ",
           "No "};
         int n = JOptionPane.showOptionDialog(panel,"SUCCESSFULLY UPDATED THE BOOK \n WOULD YOU LIKE TO UPDATE ANOTHER BOOK","STATUS",
           JOptionPane.YES_NO_CANCEL_OPTION,
           JOptionPane.QUESTION_MESSAGE,
           null,
           options,
           options[1]);
         if(n==JOptionPane.YES_NO_OPTION){
        	 cardManager.show(admnPane, "UPDATE BOOK");
        	    updateIDTF.setText(null);
				updateNOTF.setText(null);
         }
         else{
        	 cardManager.show(admnPane, "OP PANEL");
         }
		}
	}
	void viewallBooks(){
		printBook.main(null);
	}
	void allotBOOK(int BID,int prn,String studentName){
		int no_ofpieceCheck=0;
		String book_name=null;
		Statement stmt1=null,stmt2=null,stmt3=null;
		ResultSet reset1;
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		conn=db.getConn();
		try {
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
		} catch (SQLException e1){
			e1.printStackTrace();
		}
		try{
		    String sql1="select * from shelf where book_id='"+BID+"'";
			try {
				reset1=stmt1.executeQuery(sql1);
				while(reset1.next()){
					book_name=reset1.getString(2);
					no_ofpieceCheck=reset1.getInt(5);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(no_ofpieceCheck==0){
				DBconnection dclose = new DBconnection();
				dclose.dbClose();
				JOptionPane.showMessageDialog(null, "Specified book is not available");
				allotIDTF.setText(null);
				allotPRNTF.setText(null);
				cardManager.show(admnPane, "ALLOT BOOK");
			}
			else{
				try {
					String sql2="update student set book_inhand='"+book_name+"',status='"+status1+"' where pnr='"+prn+"'";
					stmt2.execute(sql2);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try{
			      String sql3="update shelf set pieces=(pieces-1) where book_id='"+BID+"'";
			      stmt3.execute(sql3); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
		   }
		   finally{
				try {
						conn.setAutoCommit(true);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Book issued....Redirecting");
					DBconnection dclose = new DBconnection();
					dclose.dbClose();
					Connection getCon=db.OneEachDBOP();
					String sql4="insert into $tableName values ('"+BID+"','"+book_name+"','"+status1+"')";
					String table=(studentName+prn);
					String sqlquery=sql4.replace("$tableName", table);
					try {
						PreparedStatement stmt5=getCon.prepareStatement(sqlquery);
						stmt5.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					db.closeEachDB();
					Object[] options = {"Yes ",
			           "No "};
  int n = JOptionPane.showOptionDialog(panel,"SUCCESSFULLY ALLOTTED THE BOOK \n WOULD YOU LIKE TO ALLOT ANOTHER BOOK","STATUS",
			           JOptionPane.YES_NO_CANCEL_OPTION,
			           JOptionPane.QUESTION_MESSAGE,
			           null,
			           options,
			           options[1]);
			         if(n==JOptionPane.YES_NO_OPTION){
			        	 cardManager.show(admnPane, "ALLOT BOOK");
			        	 allotIDTF.setText(null);
							allotPRNTF.setText(null);
							allotSTDNTF.setText(null);
			         }
			         else{
			        	 cardManager.show(admnPane, "OP PANEL");
			         }
				} 
	}
	void returnBook(int bookID,int prnE, String STDname){
		Statement stmt1=null,stmt2=null;
		 String sql5="update shelf set pieces=(pieces+1) where book_id='"+bookID+"'";
		 String sql6="update student set status='"+status2+"' where pnr='"+prnE+"'";
		 Cybrarian db = new Cybrarian();
		 db.dBCaller();
		 conn=db.getConn();
		 try {
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 try {
			stmt1.execute(sql5);
			stmt2.execute(sql6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBconnection dclose = new DBconnection();
			dclose.dbClose();
			Cybrarian dcon = new Cybrarian();
			Connection getCon=dcon.OneEachDBOP();
			String sql4="update $tableName set status='"+status2+"' where book_id='"+bookID+"'";
			String table=(STDname+prnE);
			String sqlquery=sql4.replace("$tableName", table);
			try {
				PreparedStatement stmt5=getCon.prepareStatement(sqlquery);
				stmt5.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dcon.closeEachDB();
			Object[] options = {"Yes ",
	           "No "};
int n = JOptionPane.showOptionDialog(panel,"SUCCESSFULLY RETURNED THE BOOK \n WOULD YOU LIKE TO RETURN ANOTHER BOOK","STATUS",
	           JOptionPane.YES_NO_CANCEL_OPTION,
	           JOptionPane.QUESTION_MESSAGE,
	           null,
	           options,
	           options[1]);
	         if(n==JOptionPane.YES_NO_OPTION){
	        	 cardManager.show(admnPane, "RETURN BOOK");
	        	 returnIDTF.setText(null);
				 returnPRNTF.setText(null);
	         }
	         else{
	        	 cardManager.show(admnPane, "OP PANEL");
	         }
		}
	 }
	void addStudent(int pnr,String name){
		int check=0;
		  try{
		  String book="";
		  String stat="";//just to specify in query
		  Cybrarian db = new Cybrarian();
		  db.dBCaller();//through cybrosys
		  Statement stmnt=db.getStatementobj();
			String sql="insert into student (pnr,name,book_inhand,status) values ('"+pnr+"','"+name+"','"+book+"','"+stat+"')";
			try {
				stmnt.executeUpdate(sql);
			}catch(MySQLIntegrityConstraintViolationException r){
				JOptionPane.showMessageDialog(null, "This PRN is already assigned with another student");
				prnTF.setText(null);
				check=1;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		  }
			finally{
				System.out.println("Updated student database");
				DBconnection dclose = new DBconnection();
				dclose.dbClose();
				Cybrarian db = new Cybrarian();
				Connection getCon=db.OneEachDBOP();
				String sql4="create table $tableName (book_id int(10),book_name varchar(40),status varchar(10))";
				String table=(name+pnr);
				String sqlquery=sql4.replace("$tableName", table);
				try {
					PreparedStatement stmt5=getCon.prepareStatement(sqlquery);
					stmt5.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				db.closeEachDB();
				System.out.println("Redirecting...");
				Object[] options = {"Yes ",
		           "No "};
				if(check==0){
int n = JOptionPane.showOptionDialog(panel,"SUCCESSFULLY ADDED STUDENT \n WOULD YOU LIKE TO ADD ANOTHER STUDENT","STATUS",
		           JOptionPane.YES_NO_CANCEL_OPTION,
		           JOptionPane.QUESTION_MESSAGE,
		           null,
		           options,
		           options[1]);
		         if(n==JOptionPane.YES_NO_OPTION){
		        	 cardManager.show(admnPane, "STUDENT DATA BASE");
		        	 prnTF.setText(null);
					 stdnameTF.setText(null);
		         }
		         else{
		        	 cardManager.show(admnPane, "OP PANEL");
		         }
		         check=0;
			   }
				
			}	
		}
	
	/*-------------------------------------------------------------------------------------------*/
	
	void printStudentDetails(int prnE, String stdNAME){
	    PRN=prnE;
	    StudentName=stdNAME;
	    System.out.println(StudentName);
	    Cybrarian dcon = new Cybrarian();
		Connection getCon=dcon.OneEachDBOP();
		String sql4="select * from $tableName";
		String table=(stdNAME+prnE);
		String sqlquery=sql4.replace("$tableName", table);
		try {
			PreparedStatement stmt5=getCon.prepareStatement(sqlquery);
			rsforprint=stmt5.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		printStudent.main(rsforprint,StudentName,PRN);
		 Object[] options = {"Yes ",
         "No "};
		int n = JOptionPane.showOptionDialog(panel,"RESULT SAVED IN A DOCUMENT FOR PRINTING \n WOULD YOU LIKE TO SEARCH ANOTHER STUDENT","STATUS",
		           JOptionPane.YES_NO_CANCEL_OPTION,
		           JOptionPane.QUESTION_MESSAGE,
		           null,
		           options,
		           options[1]);
		         if(n==JOptionPane.YES_NO_OPTION){
		        	 cardManager.show(admnPane, "PRINT STUDENT");
		        	 printPRNTF.setText(null);
		         }
		         else{
		        	 cardManager.show(admnPane, "OP PANEL");
		         }
     }
  void connectAdmnDB(){
	    Statement stmt=null;
		String sql="insert into admindb values('"+nameAdmin+"','"+adminpassword+"')";
		Cybrarian db = new Cybrarian();
		db.dBCaller();
		stmt=db.getStatementobj();
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBconnection dclose = new DBconnection();
			dclose.dbClose();
			JOptionPane.showMessageDialog(null, "Created a new administrator account");
			cardManager=(CardLayout) admnPane.getLayout();
			cardManager.show(admnPane, "adminlogin");
		}
    }
  void validateadmindb(){
	  int flag1=0;
	  ResultSet reset=null;
	  String nameVD[] = new String[100];
	  Statement stmt=null;
	  String sql="select pass from admindb";
	  Cybrarian db = new Cybrarian();
		db.dBCaller();
		stmt=db.getStatementobj();
		try {
			reset=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int i=0;
			while(reset.next()){
			  nameVD[i]=reset.getString(1);
			  if(nameVD[i].equalsIgnoreCase(passWord)){
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
	
		  if(flag1>0){
		     JOptionPane.showMessageDialog(null, "Authentication validated");
		     cardManager=(CardLayout) admnPane.getLayout();
		     cardManager.show(admnPane, "OP PANEL");
		   }
		 else{
			 JOptionPane.showMessageDialog(null, "Enter correct password");
		    } 
	    }	
    }
}
