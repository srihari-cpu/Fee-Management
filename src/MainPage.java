import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainPage {
	
	JFrame frame;
	JButton admin,employee;
	JLabel header;

	public MainPage() {
		frame = new JFrame("DevElet Institute of Technology");		
		admin =new JButton("ADMIN LOGIN");
		employee = new JButton("EMPLOYEE LOGIN");
		header=new JLabel("MRCET Institutte of Technology");
		
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(admin);
		container.add(employee);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminLogin();
			}
		});
		
		employee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmployeeLogin();
			}
		});
	}
	
	public static void main(String[] args) {
		new MainPage();
	}
}
