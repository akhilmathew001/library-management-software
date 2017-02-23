package libraryGUI;

import java.awt.EventQueue;   

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class printStudent extends JFrame {
    static Object data[][];
    static ResultSet receivedRS;
    static String stdname;
	private JPanel contentPane;
	private JTable table;
	static int PRN=0;
	ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(ResultSet rcvdRS, String Name, int prn) {
		System.out.println("test");
		receivedRS=rcvdRS;
		stdname=Name;
		PRN=prn;
		System.out.println(stdname);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					printStudent frame = new printStudent();
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
	public printStudent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Akhil Mathew\\workspace\\Data Base\\Images\\Library.ico"));
		setTitle("DETAILS OF STUDENT");
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
		menuBar.setBounds(0, 0, 490, 21);
		panel.add(menuBar);
		
		JMenu mainMenu = new JMenu("File");
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
		
		JLabel stdnameTABLE = new JLabel("New label");
		stdnameTABLE.setFont(new Font("Showcard Gothic", Font.BOLD, 12));
		stdnameTABLE.setBounds(122, 24, 206, 36);
		stdnameTABLE.setText(stdname);
		panel.add(stdnameTABLE);
		contentPane.setLayout(gl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 71, 480, 351);
		panel.add(scrollPane);
		table = new JTable();
		table.setModel(DbUtils.resultSetToTableModel(receivedRS));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Details of student:");
		lblNewLabel.setBounds(10, 32, 102, 14);
		panel.add(lblNewLabel);
			
	}
	@SuppressWarnings("deprecation")
	void printDATA(){
		String mysqlusername=MySQLconfiguration.getuser();
		String mysqlpassword=MySQLconfiguration.getpass();
		int mysqlport=MySQLconfiguration.getport();
        Connection con;
        String sql="select * from $table";
        String table=(stdname+PRN);
		String sqlquery=sql.replace("$table", table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:"+mysqlport+"/oneeachdb",mysqlusername,mysqlpassword);
			PreparedStatement st = con.prepareStatement(sqlquery);
	        rs = st.executeQuery();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
     
		    HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet("Student details");
		    HSSFRow rowhead = sheet.createRow((short) 0);
		    rowhead.createCell((short) 0).setCellValue("BOOK ID");
		    rowhead.createCell((short) 1).setCellValue("BOOK NAME");
		    rowhead.createCell((short) 2).setCellValue("STATUS");
		    int i = 1;
		    try {
				while (rs.next()){
				    HSSFRow row = sheet.createRow((short) i);
				    row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(1)));
				    row.createCell((short) 1).setCellValue(rs.getString(2));
				    row.createCell((short) 2).setCellValue(rs.getString(3));
				    i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    String yemi = "C:/i-LibraryDetailsAdmin/"+stdname+".xls";
		    try{
		    FileOutputStream fileOut = new FileOutputStream(yemi);
		    workbook.write(fileOut);
		    fileOut.close();
		    }catch (FileNotFoundException e3) {
		        e3.printStackTrace();
		    } catch (IOException e4) {
		        e4.printStackTrace();
		    }
		
		JOptionPane.showMessageDialog(null, "Writing complted. Check it in the folder C:/i-LibraryDetailsAdmin");
	}
}
