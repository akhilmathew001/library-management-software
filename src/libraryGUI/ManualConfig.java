package libraryGUI;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ManualConfig {

	private JFrame frmManualConfiguration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualConfig window = new ManualConfig();
					window.frmManualConfiguration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManualConfig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManualConfiguration = new JFrame();
		frmManualConfiguration.setResizable(false);
		frmManualConfiguration.setTitle("MANUAL CONFIGURATION");
		frmManualConfiguration.setBounds(100, 100, 948, 454);
		frmManualConfiguration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmManualConfiguration.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Follow the below operations to manually configure your i-Library application");
		lblNewLabel.setBounds(10, 11, 516, 17);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 12));
		frmManualConfiguration.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please restart application after configuring");
		lblNewLabel_1.setBounds(10, 390, 516, 15);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 12));
		frmManualConfiguration.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 922, 340);
		frmManualConfiguration.getContentPane().add(scrollPane);
		
		JTextArea txtarea = new JTextArea();
		txtarea.setFont(new Font("Dialog", Font.BOLD, 14));
		txtarea.setEditable(false);
		txtarea.setText(
				".......................................................................Read me file..................................................................\n"+
				"\n!!!!!....................Important info.............................!!!!!!\n"+
				".......Follow the exact way as defined as follows......\n"+
				".......Dont forget to put semicolumn after every query.....\n"+
				"!!!!!....................Important info.............................!!!!!!\n"+
                "\nRecommended supporting softwares..\n"+
				"\n     1: Java JRE 1.7 version software\n"+
                "\n     2: MySQL software version 5.1\n"+
				"........................................................................................................\n"+
				"\nStep 1: Install any version of MySQL. Follow the configuration file of the MySQL to complete the installation"+ 
				            " without any error \nMake sure that you set MySQL port number as '3306'\n---(MySQL version 5.1 is preffered)--\n"+

				"\nStep 2: Open MySQL command Line Clint..\n"+

				"\nStep 3: Type in the command prompt as follows\n"+

				"\nStep 4: create database if not exists library_books;\n"+

				"\nStep 5: press enter\n"+
				             "\n(If the typed query is correct, it will give Query OK... If it says error, then check your typed query)\n"+

				"\nStep 6: use library_books; \n(it will give: Database changed)\n"+

				"\nStep 7: press enter\n"+ 
				            "\n(If the typed query is correct, it will give Query OK... If it says error, then check your typed query)\n"+

				"\nStep 8: create table shelf (book_id int(5),book_name varchar(255), author varchar(255),department varchar(255), pieces int(10));\n"+

				"\nStep 9: press enter\n"+

				"\nStep 10: create table student (pnr int(5), name varchar(255), book_inhand varchar(255), status varchar(20));\n"+

				"\nStep 11: press enter\n"+

				"\nStep 12: create database if not exists oneeachdb;\n"+

				"\nStep 13: press enter\n"+

				"\nStep 14: close mysql command prompt\n"+

				"\n!!!!!.....................Important info.....................!!!!!!\n"+
				"\n...........Don't forget to restart the application..........\n"+

				"\nCreated: 10:42 PM 11/19/2015\n");
		scrollPane.setViewportView(txtarea);
		
		JButton ntpad = new JButton("OPEN IN A NOTEPAD");
		ntpad.setFont(new Font("Segoe Script", Font.BOLD, 12));
		ntpad.setBounds(650, 387, 183, 23);
		frmManualConfiguration.getContentPane().add(ntpad);
		ntpad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("C:/iLibrary images/Readme.txt");
				try {
					Desktop.getDesktop().edit(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
