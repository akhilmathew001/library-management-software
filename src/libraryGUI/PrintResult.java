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
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PrintResult extends JFrame {
    static Object data[][];
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		data=StudentPage.getTable();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintResult frame = new PrintResult(data);
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
	public PrintResult(Object data[][]) {
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
		exitMenu.setIcon(new ImageIcon("C:\\iLibrary images\\Delete_Icon.png"));
		exitMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		mainMenu.add(exitMenu);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 23, 480, 399);
		panel.add(scrollPane);
		/*Object[][] data2 = new Object[][] {
	            {1, "John", "william", "ec",4 },
	            {1, "John", "william", "ec",4 },
	            {1, "John", "william", "ec",4},
	            {1, "John", "william", "ec",4},
	            {51, "John", "william", "ec",4}
	        };*/
		String tabHead[]={"BOOK ID","BOOK NAME","AUTHOR","DEPARTMENT","NO. OF PIECES"};
		table = new JTable(data,tabHead);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
	}
	void printDATA(){
		
		JOptionPane.showMessageDialog(null, "File created already, check folder C:/i-LibraryDetailsstudent");
	}
}
