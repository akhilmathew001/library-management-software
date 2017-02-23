package libraryGUI;

import java.awt.EventQueue;        

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Component;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Color;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -8028073196367869905L;
	private JPanel contentPane;
	Image bgimage = null;
	public static int flag=2;
	public static int option=0;
	public JButton welcomeButton,infobtn;
	private JPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("i-Library");
		setResizable(false);
		setFont(new Font("Magneto", Font.BOLD, 13));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\iLibrary images\\Library.png"));
		setTitle("i-Library");
		setBounds(100, 100, 860, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 688, 622);
		label.setIcon(new ImageIcon("C:\\iLibrary images\\bg1.png"));
		contentPane.add(label);
		
		welcomeButton = new JButton("WELCOME");
		welcomeButton.setFont(new Font("Segoe Script", Font.BOLD, 12));
		welcomeButton.setLocation((int)CENTER_ALIGNMENT, (int)CENTER_ALIGNMENT);
		welcomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foldercheck();
				mysqlconfig();
			}
		});
		welcomeButton.setIcon(null);
		welcomeButton.setBounds(10, 147, 126, 40);
		
		infobtn = new JButton("INFO");
		infobtn.setBounds(37, 401, 77, 33);
		infobtn.setBackground(new Color(255, 228, 225));
		infobtn.setFont(new Font("Segoe Print", Font.BOLD, 13));
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(698, 11, 146, 457);
		contentPane.add(panel);
		panel.setLayout(null);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{welcomeButton}));
		panel.add(welcomeButton);
		panel.add(infobtn);
		infobtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JLabel[] arr = {new JLabel("i-LIBRARY"),new JLabel("DEVELOPED BY: AKHIL MATHEW"),new JLabel("VERSION 1.01"), new JLabel("2015"),new JLabel("COPY RIGHT PROTECTED"),
						                                          new JLabel("Customer support: akhilmakm@gmail.com")};
				JOptionPane.showMessageDialog(null, arr,"DEVELOPER INFO",JOptionPane.PLAIN_MESSAGE);
			}
			
		});
	}
	
	void foldercheck(){
		System.out.println("checking");
		Path path1=Paths.get("C:/i-LibraryDetailsstudent");
		Path path2=Paths.get("C:/i-LibraryDetailsAdmin");
		
		if (Files.exists(path1,LinkOption.NOFOLLOW_LINKS)) {
		   System.out.println("student folder exist");
		}
		else{
		File folder = new File("C:/i-LibraryDetailsstudent");
		folder.mkdir();
		}
		
		if(Files.exists(path2,LinkOption.NOFOLLOW_LINKS)){
			System.out.println("Admin folder exists");
		}
		else{
			File adminfolder = new File("C:/i-LibraryDetailsAdmin");
			adminfolder.mkdir();
		}
	}
	
	void mysqlconfig(){
		MySQLconfiguration.main(null);
		setVisible(false);
		dispose();
	}
	void closemainwindow(){
		setVisible(false);
		dispose();
	}
}
