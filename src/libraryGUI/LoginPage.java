package libraryGUI;

import java.awt.EventQueue; 

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel loginPane;
	JPanel Logpanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPage() {
		setResizable(false);
		setTitle("i-Library: LOGIN PAGE");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\iLibrary images\\apply (1).png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 508);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPane);
		
		
		Logpanel = new JPanel();
		Logpanel.setForeground(Color.WHITE);
		Logpanel.setBorder(new TitledBorder("Selct login mode"));
		Logpanel.setBackground(SystemColor.activeCaption);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon("C:\\iLibrary images\\login.jpg"));
		GroupLayout gl_loginPane = new GroupLayout(loginPane);
		gl_loginPane.setHorizontalGroup(
			gl_loginPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(Logpanel, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(284, Short.MAX_VALUE))
		);
		gl_loginPane.setVerticalGroup(
			gl_loginPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPane.createSequentialGroup()
					.addGap(82)
					.addGroup(gl_loginPane.createParallelGroup(Alignment.LEADING)
						.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
						.addComponent(Logpanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		Logpanel.setLayout(null);
		
		JLabel lblStudentLogin = new JLabel("Student login:");
		lblStudentLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentLogin.setBounds(120, 86, 89, 14);
		Logpanel.add(lblStudentLogin);
		
		JButton studentButton = new JButton("STUDENT");
		studentButton.setFont(new Font("Segoe Script", Font.BOLD, 11));
		studentButton.setBounds(283, 72, 139, 45);
		studentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//studentFrame();
				setVisible(false);
				StudentPage.main(null);
				dispose();
				
			}
		});
		Logpanel.add(studentButton);
		
		JLabel lblAdminLogin = new JLabel("Admin login:");
		lblAdminLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdminLogin.setBounds(120, 177, 103, 14);
		Logpanel.add(lblAdminLogin);
		
		JButton adminButton = new JButton("ADMIN");
		adminButton.setFont(new Font("Segoe Script", Font.BOLD, 11));
		adminButton.setBounds(283, 163, 139, 45);
		adminButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AdminPage.main(null);
				dispose();
			}
		});
		Logpanel.add(adminButton);
		loginPane.setLayout(gl_loginPane);
	}
}
